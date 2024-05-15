import csv
import os
from datetime import datetime
import re
import urllib.request
import pymysql

def main():
    baseurl = "https://gnn.gamer.com.tw/"
    # 1.爬取网页  2.在爬取数据过程中逐一解析数据
    datalist = getData(baseurl)
    # 3.保存数据
    save_data_to_csv(datalist, 'GameNews.csv')


# 创建正则表达式
#创建正则表达式，表示规则
#片名
findName = re.compile(r'</div><a href="//gnn.gamer.com.tw/.*?">(.*?)</a><img src="')
findName1 = re.compile(r'</div><a href="//gnn.gamer.com.tw/.*?">(.*?)</a><span>')

findDate = re.compile(r'</p><time dateTime="(.*?)" class="')

# 链接
findLink = re.compile(r'<a href="(.*?)"><img src="', re.S)


findImg = re.compile(r'<a href="//.*"><img src="(.*?)" srcset=')


findall = re.compile(r'<div class="GN-lbox2B"><div class="GN-lbox2E">(.*?)繼續閱讀</a></p></div>', re.S)
chinese_pattern = r'[\u4e00-\u9fa5，。？！：；、（）【】《》‘’“”…￥—]+'  # 匹配中文字符




# 爬取网页与逐一解析数据
def getData(baseurl):
    datalist = []
    url = baseurl
    html = askURL(url)  # 保存获取的网页源码
    html = re.sub("\n", "", html)
    contents = []
    print(html)
    # 2.逐一解析数据
    matches = re.findall(findall, html)

    for tbody in matches:  # 查找符合要求的字符串,行形成列表
        print(tbody)  #对爬取的一个番剧的信息进行输出测试
        data = []  # 保存一个番剧的所有信息
        tbody = str(tbody)

        Name = re.findall(findName, tbody)
        if len(Name) == 0:
            Name = re.findall(findName1, tbody)
        Name = ''.join(Name)
        Name = str(Name)

        data.append(Name)

        print(Name)

        img = re.findall(findImg, tbody)
        if len(img) == 0:
            img = '未知'
        img = ''.join(img)
        img = re.sub(" ", '', str(img))
        data.append(str(img))

        Link = re.findall(findLink,tbody)
        Link = ''.join(Link)
        Link = str(Link)
        data.append(Link)

        datalist.append(data)  # 把一部番剧数据放入datalist
    for data in datalist:
        print(data)
    print(len(datalist))
    return datalist



# 得到一个网页的数据
def askURL(url):
    head = {
        "User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.55 Safari/537.36 Edg/96.0.1054.43"
    }#模拟头部，进行伪装
#此处User-Agent的内容为前一步中复制的内容
    request = urllib.request.Request(url,headers=head)
    html = ""
    try:
        response = urllib.request.urlopen(request)
        html = response.read().decode("utf-8")
        #print(html)
    except urllib.error.URLError as e:
        if hasattr(e,"code"):
            print(e.code)
        if hasattr(e,"reason"):
            print(e.reason)
    return html


def save_data_to_csv(new_datalist, filename):
    # 如果文件不存在，则直接保存新爬取的数据
    if not os.path.exists(filename):
        with open(filename, mode='w', newline='', encoding='utf-8') as file:
            writer = csv.writer(file)
            writer.writerow(['title', 'image', 'link'])
            writer.writerows(new_datalist)
        print("已创建并保存数据到", filename)
        return

    # 如果文件存在，读取已有的数据
    existing_data = read_existing_data(filename)
    all_data = existing_data
    # 找出新爬取的数据中哪些记录是原有CSV文件中没有的
    new_records = [record for record in new_datalist if record not in existing_data]

    if new_records:
        all_data = new_records + existing_data


    # 写入排序后的数据到文件中
    with open(filename, mode='w', newline='', encoding='utf-8') as file:
        writer = csv.writer(file)
        writer.writerow(['title', 'image', 'link'])
        writer.writerows(all_data)
    print("已保存并根据日期排序后的数据到", filename)


def read_existing_data(filename):
    with open(filename, mode='r', newline='', encoding='utf-8') as file:
        reader = csv.reader(file)
        existing_data = [row for row in reader]
        # 跳过标题行
        existing_data = existing_data[1:]
    return existing_data




if __name__ == "__main__":
    main()
    print("爬取完毕！")