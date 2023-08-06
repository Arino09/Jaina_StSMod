# Steam Workshop Mod | Steam 创意工坊 Mod

## 上传方法
1. `maven package` 打包源代码
2. 将 jar 包放到 content 目录中
3. 修改配置信息
4. 运行 `upload_mod.bat`

## Directory layout | 目录结构
* config.json -- The config describing the Steam workspace mod. | 创意工坊 mod 的配置文件
* content     -- Where you should place the mod files to be uploaded to Steam Workshop. | 将待上传的文件放到此处 
                 Don't put source code in here, just end products like jar files. | 不要将源代码而是将 jar 包放在这里
* image.jpg   -- The default image. Replace with your own! | 创意工坊物品封面
* README.md   -- This readme document | readme 文档

## The config.json | config.json 配置
{
  "title": "",                <-- The title of your mod. | mod标题
  "description": "",          <-- The description. | mod描述
  "visibility": "private",    <-- The visibility status of the mod. | 可见性 
                                  Options include: "private", "friends", "friendsonly", "public". | 选项包括：私人、仅好友可见、公开
  "changeNote": "",           <-- A note for describing the newest changes you've made to your users. | 变更描述信息
  "tags": []                  <-- A list of tags to search for your mod by. | 标签列表
                                  Note: the tag "tool" is reserved for mods that function as tools.
}





