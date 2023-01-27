杀戮尖塔mod制作教程
=====================

每张卡牌写法也大同小异，可参考打击和防御牌。
主要修改构造方法中的卡牌参数，use中触发的动作，以及升级后的效果即可。
遇到不会的想实现的效果先看官方api

# 一些实用的工具/网站

## 目录
* [网站](#网站)
* [工具](#工具)
* [mod样板](mod样板)
* [动画](#动画)
* [和尖塔无关但你也许需要](#和尖塔无关但你也许需要)

## 网站
* [ModTheSpire Wiki](https://github.com/kiooeht/ModTheSpire/wiki)<br>
  <b>ModTheSpire</b>（简称MTS）是一种无需修改基础游戏文件即可为 Slay the Spire 加载外部模组的工具，同时允许模组将自己的代码修补到游戏代码中。<br>
  MTS Wiki上写了如何进行全局保存、patch等。


* [BaseMod Wiki](https://github.com/daviscook477/BaseMod/wiki)<br>
  <b>BaseMod</b>是模组的基础API，能够让mod作者方便的向游戏中添加自己的卡牌等内容并且集中管理这些内容。<br>
  Wiki上写了一些很实用的小工具，例如自动注册所有卡牌（AutoAdd）、卡牌修改器（CardModifier）、一局游戏内保存（CustomSavable）等。也包括BaseMod作者写的mod制作教程。

## 工具
* [JD-GUI](http://java-decompiler.github.io/)<br>
  一个Java反编译工具，具有GUI界面。<br>
  可以让你查看游戏或其他mod重构后的源代码方便~~拷贝~~学习其他人的代码。<br>
  也可以用来查询打patch需要的行数。（idea自带的反编译不准确）

* [sts裁图器](https://github.com/JohnnyBazooka89/StSModdingToolCardImagesCreator)<br>
  把图片裁剪成尖塔卡图需要的形状和尺寸。<br>
  我并没有用过这个，群里有群友自己制作的另一个相同功能的工具。

## mod样板
* [战神徽章mod](https://github.com/Rita-Bernstein/Warlord-Emblem)
 
 - 比较标准化的一个mod范例。

## 动画
*制作动画需要一些基础，但其实大多数mod只需要一张图就够了。*
* [Spine](http://zh.esotericsoftware.com/)<br>
  尖塔使用的2D动画软件，要钱的，不推荐。

* [龙骨](https://dragonbones.github.io/cn/index.html)<br>
  可以导出spine动画的软件，目前网站无法下载可用版本（只能使用最新版），可以来自己找或者来群里要。

## 和尖塔无关但你也许需要
* [SourceGraph](https://sourcegraph.com/search)<br>
  一个可以快速浏览Github储存库的网站（有Chrome扩展程序，可以在Github页面点击进入相应网页），用起来比Github浏览速度快，并且代码结构清晰，可以在Github抽风时使用。

* [Github文件加速](https://gh.api.99988866.xyz/)<br>
  一个可以快速下载Github Release的网站。