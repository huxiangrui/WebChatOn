# 巧克力爱好者匿名

---

## 项目来源
武汉大学开源软件工程
网址[https://github.com/huxiangrui/WebChatOn](https://github.com/huxiangrui/WebChatOn)
--- 

## 环境设置
* jdk1.7以上
* Tomcat7以上
* jquery3以上
* dom4j

---
## 项目简介
本项目实现了一个针对巧克力爱好者的管理软件，用于巧克力爱好者协会的管理，包括人员管理、账单管理以及报表生成。  
项目分为前端用户界面和后端信息处理两个部分，前端与后台的交互采用jsp与ajax结合的形式进行交互， 数据存储在xml文件之中，并且采用XML Schema的文件对每一个数据的数据结构进行约束。在对XML操作上，本项目用dom4j作为操作库。

---
## 代码目录结构
WebChatOn
* src         --后端代码
    * object             --基础数据对象
    * path               --获取程序地址
    * report       --    --报表格式
    * reportreader       --生成报表
    * reportservice      --报表子部分
    * reportwriter       --将报表数据写入存储文件
    * ui                 --起始界面交互
    * uiEFT              --金融报表界面交互
    * uimanager          --管理者界面交互
    * uioperator         --操作员界面交互
    * user               --基础数据存储
* WebRoot     --前端代码
    * js                 --前端依赖的js包	
    * jsp                --前端页面
    * report             --报表存储文件
    * user               --基础数据存储文件
    
---
## 安装说明
 本软件可直接用eclipse导入，但是要修改项目配置中的jdk版本