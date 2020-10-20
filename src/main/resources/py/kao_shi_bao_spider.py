# coding=utf-8
import time
import datetime
from splinter import Browser
from selenium import webdriver
from lxml import etree
import pymysql
import sys
import os
import re
os.environ['NLS_LANG'] = 'SIMPLIFIED CHINESE_CHINA.UTF8'

# 打开数据库连接
dev_db = pymysql.connect("localhost", "rymcu_subject", "1234", "rymcu_subject")
rymcu_test_db = pymysql.connect(
    "120.26.175.127", "maomaochong", "maomaochong.MMC", "subject")
db = rymcu_test_db
question_types = {
    "单选题": 1,
    "多选题": 2,
    "判断题": 3,
    "填空题": 4,
    "简答题": 5,
    "0": 0
}

# SQL 插入语句
sql_insert_subject_question = '''
insert into subject_question( QUESTION_TYPE,QUESTION_LEVEL,REMARK,QUESTION_URL,QUESTION_URL_REMARK,
    SRC_TYPE,QUESTION_CONTENT,CREATED_BY,CREATED_TIME)
values( %s, %s ,%s ,%s ,%s,
     %s,%s,%s,%s)
'''

sql_insert_subject_option = '''
insert into subject_option( SUBJECT_QUESTION_ID,OPTION_NAME,OPTION_CONTENT,IS_ANSWER)
values( %s, %s ,%s ,%s)
'''

# chromedriver
chromeDriverPath = 'C:\Program Files\Google\Chrome\Application/chromedriver.exe'
options = webdriver.ChromeOptions()
options.add_argument('--ignore-certificate-errors')
options.add_experimental_option('excludeSwitches', ['enable-logging'])
browser = webdriver.Chrome(
    executable_path=chromeDriverPath, chrome_options=options)
f = open('./kao_shi_bao_spider.md', 'a+')

# 格式化选项列表


def format_selection_list(old_selection_list: list):
    new_selection_list = []
    selection_count = len(old_selection_list) / 3
    selection_index = 0
    while selection_index < selection_count:
        old_selection_index = 3 * selection_index
        selection_name = old_selection_list[old_selection_index][0]
        selection_value = old_selection_list[old_selection_index + 2].strip()
        selection_index = selection_index + 1
        new_selection_list.append([selection_name, selection_value])
    return new_selection_list

# 存储为md格式


def save_md(question, selection_lsit, answer, index, question_type_str):
    question = '##### ' + question_type_str + str(index)+'、' + question + '\n'
    answer = "".join(answer)
    answer = '```\n正确答案： '+answer.lstrip('\n')+'\n```\n'
    selection_md = ""
    for seleciton_name, selectin_value in selection_lsit:
        selection_md = selection_md + "+ " + seleciton_name + "、" + selectin_value + "\n"
    f.write(question+selection_md+answer)

# 存储到mysql数据库


def save_mysql(question, selection_list, answer, question_type_str, subject_question_url):
    cursor = db.cursor()
    question_type_int = question_types.get(question_type_str)
    cursor.execute(sql_insert_subject_question,(question_type_int, 10, '脚本生成测试',subject_question_url, "考试宝", 4, question, 0, datetime.datetime.now()))

    # 最后插入行的主键id
    subject_question_id = cursor.lastrowid
    if selection_list:
        for selection_name, selection_value in selection_list:
            is_answer = selection_name in answer
            cursor.execute(sql_insert_subject_option, (subject_question_id,
                                                       selection_name, selection_value, is_answer))
    else:
        cursor.execute(sql_insert_subject_option,
                       (subject_question_id, "答案", answer, 1))
    db.commit()
    cursor.close()

# 加载题目、选项、答案


def load_subject(url_page_source, index, have_next, url):
    tree_one = etree.HTML(url_page_source)
    question_xpath = tree_one.xpath(
        '/html/body/div[2]/div/div/section/div/div[1]/div[2]/div[1]/div/div[1]/div/span[1]/text()')
    if question_xpath:
        question_type = question_xpath[0].strip('\n')
    else:
        question_type = "0"
    question = tree_one.xpath(
        '/html/body/div[2]/div/div/section/div/div[1]/div[2]/div[1]/div/div[1]/div/div')[0].xpath('string(.)').lstrip('\n')+'\n'
    selections = tree_one.xpath(
        '/html/body/div[2]/div/div/section/div/div[1]/div[2]/div[1]/div/div[2]//div//text()')
    answer = tree_one.xpath(
        '/html/body/div[2]/div/div/section/div/div[1]/div[2]/div[3]/p[2]/span//text()')[0]
    selection_list = format_selection_list(selections)
    save_md(question, selection_list, answer, index, question_type)
    save_mysql(question, selection_list, answer,
               question_type, browser.current_url)
    print('题号: ', index, '类型', question_type, answer)
    time.sleep(2)
    index_and_count_str = tree_one.xpath(
        '/html/body/div[2]/div/div/section/div/div[1]/div[2]/div[1]/div/div[1]/div/span[2]/text()')[0].strip('\n')
    index_and_count_array = re.findall(
        '\d+', index_and_count_str.strip("、").split()[0])
    return index_and_count_array[0] != index_and_count_array[1]

# 保存并加载题目


def save_and_load_subject(url, pageCurrentUrl):
    index = 1
    have_next = True
    # 获取点击下一题按钮
    next_button = browser.find_element_by_css_selector(
        '#body > div > div.layout-container.prative-page > div.prative-box > div.topic.no-select > div > div.next-preve > button:nth-child(2)')
    # 如果存在下一题按钮，则继续加载题目
    while have_next:
        have_next = load_subject(browser.page_source, index, have_next, url)
        try:
            next_button.click()
        except:
            get_subject(url, browser.current_url)
        # 加载题目
        index = index + 1
    return

# 加载题库


def get_subject(url, pageCurrentUrl):
    # 访问题库链接
    if pageCurrentUrl:
        browser.get(pageCurrentUrl)
    else:
        browser.get(url)

    # # 等待浏览器加载
    # time.sleep(1)
    # # 点击顺序答题按钮
    # browser.find_element_by_xpath('/html/body/div[2]/div/div/section/div/div/div[1]/div[2]/div[1]/div/a[1]').click()
    # 等待浏览器加载
    time.sleep(2)
    try:
        # 点击确认继续答题按钮，可能没有这个按钮
        browser.find_element_by_css_selector(
            'body > div.el-message-box__wrapper > div > div.el-message-box__btns > button.el-button.el-button--default.el-button--small.el-button--primary').click()
        # 点击显示答案按钮
        browser.find_element_by_css_selector(
            '#body > div > div.layout-container.prative-page > div.prative-box > div.practice-ctrl > div > span:nth-child(8) > span').click()
    except:
        # 点击显示答案按钮
        browser.find_element_by_css_selector(
            '#body > div > div.layout-container.prative-page > div.prative-box > div.practice-ctrl > div > span:nth-child(8) > span').click()

    save_and_load_subject(url, pageCurrentUrl)

# 读取题库链接文本获取题库url


def load_url_list(url_file_name):
    url_List = []
    with open(url_file_name, mode='r', encoding='gbk') as f:
        for line in f:
            url = line.strip()
            if url != '':
                url_List.append(url)
    return url_List


def main(url_file_name):
    url_list = load_url_list(url_file_name)
    for url in url_list:
        get_subject(url, None)
    f.close()


if __name__ == '__main__':
    # 题库链接文件
    url_file_name = "kao_shi_bao_url.txt"
    main(url_file_name)
