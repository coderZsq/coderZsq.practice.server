## ik 分词器

```shell
$ cp -r elasticsearch-analysis-ik-6.5.4/ /usr/share/elasticsearch/plugins/
$ systemctl restart elasticsearch

http://192.168.199.164:9200/_cat/plugins
```

## kibana 中 dev tool

```json
PUT /shop_product
#! Deprecation: the default number of shards will change from [5] to [1] in 7.0.0; if you wish to continue using the default of [5] shards, you must manage this on the create index request or with an index template
{
  "acknowledged" : true,
  "shards_acknowledged" : true,
  "index" : "shop_product"
}

GET /shop_product/_analyze
{
  "text": "I am coderZsq"
}

{
  "tokens" : [
    {
      "token" : "i",
      "start_offset" : 0,
      "end_offset" : 1,
      "type" : "<ALPHANUM>",
      "position" : 0
    },
    {
      "token" : "am",
      "start_offset" : 2,
      "end_offset" : 4,
      "type" : "<ALPHANUM>",
      "position" : 1
    },
    {
      "token" : "coderzsq",
      "start_offset" : 5,
      "end_offset" : 13,
      "type" : "<ALPHANUM>",
      "position" : 2
    }
  ]
}

GET /shop_product/_analyze
{
  "text": "中国你好， 祖国强大"
}

{
  "tokens" : [
    {
      "token" : "中",
      "start_offset" : 0,
      "end_offset" : 1,
      "type" : "<IDEOGRAPHIC>",
      "position" : 0
    },
    {
      "token" : "国",
      "start_offset" : 1,
      "end_offset" : 2,
      "type" : "<IDEOGRAPHIC>",
      "position" : 1
    },
    {
      "token" : "你",
      "start_offset" : 2,
      "end_offset" : 3,
      "type" : "<IDEOGRAPHIC>",
      "position" : 2
    },
    {
      "token" : "好",
      "start_offset" : 3,
      "end_offset" : 4,
      "type" : "<IDEOGRAPHIC>",
      "position" : 3
    },
    {
      "token" : "祖",
      "start_offset" : 6,
      "end_offset" : 7,
      "type" : "<IDEOGRAPHIC>",
      "position" : 4
    },
    {
      "token" : "国",
      "start_offset" : 7,
      "end_offset" : 8,
      "type" : "<IDEOGRAPHIC>",
      "position" : 5
    },
    {
      "token" : "强",
      "start_offset" : 8,
      "end_offset" : 9,
      "type" : "<IDEOGRAPHIC>",
      "position" : 6
    },
    {
      "token" : "大",
      "start_offset" : 9,
      "end_offset" : 10,
      "type" : "<IDEOGRAPHIC>",
      "position" : 7
    }
  ]
}

GET /shop_product/_analyze
{
  "text": "中国你好， 祖国强大",
  "analyzer": "ik_smart"
}

{
  "tokens" : [
    {
      "token" : "中国",
      "start_offset" : 0,
      "end_offset" : 2,
      "type" : "CN_WORD",
      "position" : 0
    },
    {
      "token" : "你好",
      "start_offset" : 2,
      "end_offset" : 4,
      "type" : "CN_WORD",
      "position" : 1
    },
    {
      "token" : "祖国",
      "start_offset" : 6,
      "end_offset" : 8,
      "type" : "CN_WORD",
      "position" : 2
    },
    {
      "token" : "强大",
      "start_offset" : 8,
      "end_offset" : 10,
      "type" : "CN_WORD",
      "position" : 3
    }
  ]
}

GET /shop_product/_analyze
{
  "text": "中国你好， 祖国强大",
  "analyzer": "ik_max_word"
}

{
  "tokens" : [
    {
      "token" : "中国",
      "start_offset" : 0,
      "end_offset" : 2,
      "type" : "CN_WORD",
      "position" : 0
    },
    {
      "token" : "你好",
      "start_offset" : 2,
      "end_offset" : 4,
      "type" : "CN_WORD",
      "position" : 1
    },
    {
      "token" : "祖国",
      "start_offset" : 6,
      "end_offset" : 8,
      "type" : "CN_WORD",
      "position" : 2
    },
    {
      "token" : "国强",
      "start_offset" : 7,
      "end_offset" : 9,
      "type" : "CN_WORD",
      "position" : 3
    },
    {
      "token" : "强大",
      "start_offset" : 8,
      "end_offset" : 10,
      "type" : "CN_WORD",
      "position" : 4
    }
  ]
}

GET /shop_product/_analyze
{
  "text": "朱双泉你好",
  "analyzer": "ik_smart"
}

{
  "tokens" : [
    {
      "token" : "朱",
      "start_offset" : 0,
      "end_offset" : 1,
      "type" : "CN_CHAR",
      "position" : 0
    },
    {
      "token" : "双",
      "start_offset" : 1,
      "end_offset" : 2,
      "type" : "CN_CHAR",
      "position" : 1
    },
    {
      "token" : "泉",
      "start_offset" : 2,
      "end_offset" : 3,
      "type" : "CN_CHAR",
      "position" : 2
    },
    {
      "token" : "你好",
      "start_offset" : 3,
      "end_offset" : 5,
      "type" : "CN_WORD",
      "position" : 3
    }
  ]
}
```

## 添加自定义分词

```shell
$ cd /usr/share/elasticsearch/plugins/elasticsearch-analysis-ik-6.5.4/config
$ less main.dic
shift+g
朱双泉

$ systemctl restart elasticsearch
```

```json
GET /shop_product/_analyze
{
  "text": "朱双泉你好",
  "analyzer": "ik_smart"
}

{
  "tokens" : [
    {
      "token" : "朱双泉",
      "start_offset" : 0,
      "end_offset" : 3,
      "type" : "CN_WORD",
      "position" : 0
    },
    {
      "token" : "你好",
      "start_offset" : 3,
      "end_offset" : 5,
      "type" : "CN_WORD",
      "position" : 1
    }
  ]
}
```

## 建立索引

```json
PUT /aaa
#! Deprecation: the default number of shards will change from [5] to [1] in 7.0.0; if you wish to continue using the default of [5] shards, you must manage this on the create index request or with an index template
{
  "acknowledged" : true,
  "shards_acknowledged" : true,
  "index" : "aaa"
}

PUT /bbb
{
  "settings": {
    "number_of_shards": 2, // 分片
    "number_of_replicas": 2 // 备份
  }
}

{
  "acknowledged" : true,
  "shards_acknowledged" : true,
  "index" : "bbb"
}

```

## 删除索引

```json
DELETE /aaa
{
  "acknowledged" : true
}

DELETE /bbb
{
  "acknowledged" : true
}
```

## 建立映射

```json
DELETE /shop_product
PUT /shop_product
{
  "mappings": {
    "shop_product": {
      "properties": {
        "id": {"type": "long"},
        "name": {"type": "text", "analyzer": "ik_smart", "search_analyzer": "ik_smart"},
        "price": {"type": "double"}
      }
    }
  }
}

#! Deprecation: the default number of shards will change from [5] to [1] in 7.0.0; if you wish to continue using the default of [5] shards, you must manage this on the create index request or with an index template
{
  "acknowledged" : true,
  "shards_acknowledged" : true,
  "index" : "shop_product"
}
```

## 查询映射

```json
GET /shop_product/_mapping
{
  "shop_product" : {
    "mappings" : {
      "shop_product" : {
        "properties" : {
          "id" : {
            "type" : "long"
          },
          "name" : {
            "type" : "text",
            "analyzer" : "ik_smart"
          },
          "price" : {
            "type" : "double"
          }
        }
      }
    }
  }
}
```

## 新增和替换文档

ES 里面的数据 --> MySQL, MongoDB, HBase

"\_version" : 1 解决并发的乐观锁

```json
PUT /shop_product/shop_product/1
{
  "id": 1,
  "name": "小米手机",
  "price": 1699
}

{
  "_index" : "shop_product",
  "_type" : "shop_product",
  "_id" : "1",
  "_version" : 1,
  "result" : "created",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 0,
  "_primary_term" : 1
}

PUT /shop_product/shop_product/1
{
  "id": 1,
  "name": "小米手机",
  "price": 999
}

{
  "_index" : "shop_product",
  "_type" : "shop_product",
  "_id" : "1",
  "_version" : 2,
  "result" : "updated",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 1,
  "_primary_term" : 1
}
```

## 查询文档

```json
GET /shop_product/shop_product/1
{
  "_index" : "shop_product",
  "_type" : "shop_product",
  "_id" : "1",
  "_version" : 2,
  "found" : true,
  "_source" : {
    "id" : 1,
    "name" : "小米手机",
    "price" : 999
  }
}

GET /shop_product/shop_product/_search
{
  "took" : 48,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 1,
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "1",
        "_score" : 1.0,
        "_source" : {
          "id" : 1,
          "name" : "小米手机",
          "price" : 999
        }
      }
    ]
  }
}

PUT /shop_product/shop_product/2
{
  "id": 2,
  "name": "华为手机",
  "price": 6000
}

{
  "_index" : "shop_product",
  "_type" : "shop_product",
  "_id" : "2",
  "_version" : 1,
  "result" : "created",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 0,
  "_primary_term" : 1
}

GET /shop_product/shop_product/_search
{
  "took" : 48,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 2,
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "2",
        "_score" : 1.0,
        "_source" : {
          "id" : 2,
          "name" : "华为手机",
          "price" : 6000
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "1",
        "_score" : 1.0,
        "_source" : {
          "id" : 1,
          "name" : "小米手机",
          "price" : 999
        }
      }
    ]
  }
}
```

## 删除文档

```json
DELETE /shop_product/shop_product/2
{
  "_index" : "shop_product",
  "_type" : "shop_product",
  "_id" : "2",
  "_version" : 2,
  "result" : "deleted",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 1,
  "_primary_term" : 1
}

PUT /shop_product/shop_product/2
{
  "id": 2,
  "name": "华为手机",
  "price": 5555
}

{
  "_index" : "shop_product",
  "_type" : "shop_product",
  "_id" : "2",
  "_version" : 3,
  "result" : "created",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 2,
  "_primary_term" : 1
}

GET /shop_product/shop_product/2
{
  "_index" : "shop_product",
  "_type" : "shop_product",
  "_id" : "2",
  "_version" : 3,
  "found" : true,
  "_source" : {
    "id" : 2,
    "name" : "华为手机",
    "price" : 5555
  }
}
```

## 高级查询

```json
DELETE /shop_product/
{
  "acknowledged" : true
}

PUT /shop_product
{
  "mappings": {
    "shop_product": {
      "properties": {
        "id": {
	  "type": "long"
	},
        "title": {
          "type": "text",
          "analyzer": "ik_smart",
          "search_analyzer": "ik_smart",
          "fields": {
          "keyword": {
          "type": "keyword",
            "ignore_above": 256
            }
          }
        },
        "price": {"type": "integer"},
        "intro": {
          "type": "text",
          "analyzer": "ik_smart",
          "search_analyzer": "ik_smart",
          "fields": {
            "keyword": {
              "type": "keyword",
              "ignore_above": 256
            }
          }
        },
      	"brand": {
      	  "type": "keyword"
      	}
      }
    }
  }
}

POST /shop_product/shop_product/_bulk
{"create":{"_id": 1}}
{"id":1,"title":"Apple iPhone XR (A2108) 128GB 白色 移动联通电信4G手机 双卡双待","price":5299,"intro":"【iPhoneXR限时特惠！】6.1英寸视网膜显示屏，A12仿生芯片，面容识别，无线充电，支持双卡！选【换修无忧版】获 AppleCare 原厂服务，享只换不修！更有快速换机、保值换新、轻松月付！","brand":"Apple"}
{"create":{"_id": 2}}
{"id":2,"title":"Apple 2019款 Macbook Pro 13.3【带触控栏】八代i7 18G 256G RP645显卡 深空灰 苹果笔记本电脑 轻薄本 MUHN2CH/A","price":15299,"intro":"【八月精选】Pro2019年新品上市送三重好礼，现在购买领满8000减400元优惠神劵，劵后更优惠！","brand":"Apple"}
{"create":{"_id": 3}}
{"id":3,"title":"Apple iPad Air 3 2019年新款平板电脑 10.5英寸（64G WLAN版/A12芯片/Retina显示屏/MUUL2CH/A）金色","price":3788,"intro":"8月尊享好礼！买iPad即送蓝牙耳机！领券立减！多款产品支持手写笔！【新一代iPad，总有一款适合你】选【换修无忧版】获 AppleCare 原厂服务，享只换不修！更有快速换机、保值换新、轻松月付！","brand":"Apple"}
{"create":{"_id": 4}}
{"id":4,"title":"华为HUAWEI MateBook X Pro 2019款 英特尔酷睿i5 13.9英寸全面屏轻薄笔记本电脑(i5 8G 512G 3K 触控) 灰","price":7999,"intro":"3K全面屏开启无界视野;轻薄设计灵动有型，HuaweiShare一碰传","brand":"华为"}
{"create":{"_id": 5}}
{"id":5,"title":"华为 HUAWEI Mate20 X (5G) 7nm工艺5G旗舰芯片全面屏超大广角徕卡三摄8GB+256GB翡冷翠5G双模全网通手机","price":6199,"intro":"【5G双模，支持SA/NSA网络，7.2英寸全景巨屏，石墨烯液冷散热】5G先驱，极速体验。","brand":"华为"}
{"create":{"_id": 6}}
{"id":6,"title":"华为平板 M6 10.8英寸麒麟980影音娱乐平板电脑4GB+64GB WiFi（香槟金）","price":2299,"intro":"【华为暑期购】8月2日-4日，M5青春版指定爆款型号优惠100元，AI语音控制","brand":"华为"}
{"create":{"_id": 7}}
{"id":7,"title":"荣耀20 PRO DXOMARK全球第二高分 4800万四摄 双光学防抖 麒麟980 全网通4G 8GB+128GB 蓝水翡翠 拍照手机","price":3199,"intro":"白条6期免息！麒麟980，4800万全焦段AI四摄！荣耀20系列2699起，4800万超广角AI四摄！","brand":"荣耀"}
{"create":{"_id": 8}}
{"id":8,"title":"荣耀MagicBook Pro 16.1英寸全面屏轻薄性能笔记本电脑（酷睿i7 8G 512G MX250 IPS FHD 指纹解锁）冰河银","price":6199,"intro":"16.1英寸无界全面屏金属轻薄本，100%sRGB色域，全高清IPS防眩光护眼屏，14小时长续航，指纹一健开机登录，魔法一碰传高速传输。","brand":"荣耀"}
{"create":{"_id": 9}}
{"id":9,"title":"荣耀平板5 麒麟8核芯片 GT游戏加速 4G+128G 10.1英寸全高清屏影音平板电脑 WiFi版 冰川蓝","price":1549,"intro":"【爆款平板推荐】哈曼卡顿专业调音，10.1英寸全高清大屏，双喇叭立体环绕音，配置多重护眼，值得拥有！","brand":"荣耀"}
{"create":{"_id": 10}}
{"id":10,"title":"小米9 4800万超广角三摄 6GB+128GB全息幻彩蓝 骁龙855 全网通4G 双卡双待 水滴全面屏拍照智能游戏手机","price":2799,"intro":"限时优惠200，成交价2799！索尼4800万广角微距三摄，屏下指纹解锁！","brand":"小米"}
{"create":{"_id": 11}}
{"id":11,"title":"小米(MI)Pro 2019款 15.6英寸金属轻薄笔记本(第八代英特尔酷睿i7-8550U 16G 512GSSD MX250 2G独显) 深空灰","price":6899,"intro":"【PCIE固态硬盘、72%NTSC高色域全高清屏】B面康宁玻璃覆盖、16G双通道大内存、第八代酷睿I7处理器、专业级调校MX150","brand":"小米"}
{"create":{"_id": 12}}
{"id":12,"title":"联想(Lenovo)拯救者Y7000P 2019英特尔酷睿i7 15.6英寸游戏笔记本电脑(i7 9750H 16G 1T SSD GTX1660Ti 144Hz)","price":9299,"intro":"超大1T固态，升级双通道16G内存一步到位，GTX1660Ti电竞级独显，英特尔9代i7H高性能处理器，144Hz电竞屏窄边框！","brand":"联想"}

GET /shop_product/shop_product/1
{
  "_index" : "shop_product",
  "_type" : "shop_product",
  "_id" : "1",
  "_version" : 1,
  "found" : true,
  "_source" : {
    "id" : 1,
    "title" : "Apple iPhone XR (A2108) 128GB 白色 移动联通电信4G手机 双卡双待",
    "price" : 5299,
    "intro" : "【iPhoneXR限时特惠！】6.1英寸视网膜显示屏，A12仿生芯片，面容识别，无线充电，支持双卡！选【换修无忧版】获 AppleCare 原厂服务，享只换不修！更有快速换机、保值换新、轻松月付！",
    "brand" : "Apple"
  }
}

GET /shop_product/shop_product/2
{
  "_index" : "shop_product",
  "_type" : "shop_product",
  "_id" : "2",
  "_version" : 1,
  "found" : true,
  "_source" : {
    "id" : 2,
    "title" : "Apple 2019款 Macbook Pro 13.3【带触控栏】八代i7 18G 256G RP645显卡 深空灰 苹果笔记本电脑 轻薄本 MUHN2CH/A",
    "price" : 15299,
    "intro" : "【八月精选】Pro2019年新品上市送三重好礼，现在购买领满8000减400元优惠神劵，劵后更优惠！",
    "brand" : "Apple"
  }
}

GET /shop_product/shop_product/3
{
  "_index" : "shop_product",
  "_type" : "shop_product",
  "_id" : "3",
  "_version" : 1,
  "found" : true,
  "_source" : {
    "id" : 3,
    "title" : "Apple iPad Air 3 2019年新款平板电脑 10.5英寸（64G WLAN版/A12芯片/Retina显示屏/MUUL2CH/A）金色",
    "price" : 3788,
    "intro" : "8月尊享好礼！买iPad即送蓝牙耳机！领券立减！多款产品支持手写笔！【新一代iPad，总有一款适合你】选【换修无忧版】获 AppleCare 原厂服务，享只换不修！更有快速换机、保值换新、轻松月付！",
    "brand" : "Apple"
  }
}

GET /shop_product/shop_product/_search
{
  "took" : 78,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 12,
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "5",
        "_score" : 1.0,
        "_source" : {
          "id" : 5,
          "title" : "华为 HUAWEI Mate20 X (5G) 7nm工艺5G旗舰芯片全面屏超大广角徕卡三摄8GB+256GB翡冷翠5G双模全网通手机",
          "price" : 6199,
          "intro" : "【5G双模，支持SA/NSA网络，7.2英寸全景巨屏，石墨烯液冷散热】5G先驱，极速体验。",
          "brand" : "华为"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "8",
        "_score" : 1.0,
        "_source" : {
          "id" : 8,
          "title" : "荣耀MagicBook Pro 16.1英寸全面屏轻薄性能笔记本电脑（酷睿i7 8G 512G MX250 IPS FHD 指纹解锁）冰河银",
          "price" : 6199,
          "intro" : "16.1英寸无界全面屏金属轻薄本，100%sRGB色域，全高清IPS防眩光护眼屏，14小时长续航，指纹一健开机登录，魔法一碰传高速传输。",
          "brand" : "荣耀"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "9",
        "_score" : 1.0,
        "_source" : {
          "id" : 9,
          "title" : "荣耀平板5 麒麟8核芯片 GT游戏加速 4G+128G 10.1英寸全高清屏影音平板电脑 WiFi版 冰川蓝",
          "price" : 1549,
          "intro" : "【爆款平板推荐】哈曼卡顿专业调音，10.1英寸全高清大屏，双喇叭立体环绕音，配置多重护眼，值得拥有！",
          "brand" : "荣耀"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "10",
        "_score" : 1.0,
        "_source" : {
          "id" : 10,
          "title" : "小米9 4800万超广角三摄 6GB+128GB全息幻彩蓝 骁龙855 全网通4G 双卡双待 水滴全面屏拍照智能游戏手机",
          "price" : 2799,
          "intro" : "限时优惠200，成交价2799！索尼4800万广角微距三摄，屏下指纹解锁！",
          "brand" : "小米"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "12",
        "_score" : 1.0,
        "_source" : {
          "id" : 12,
          "title" : "联想(Lenovo)拯救者Y7000P 2019英特尔酷睿i7 15.6英寸游戏笔记本电脑(i7 9750H 16G 1T SSD GTX1660Ti 144Hz)",
          "price" : 9299,
          "intro" : "超大1T固态，升级双通道16G内存一步到位，GTX1660Ti电竞级独显，英特尔9代i7H高性能处理器，144Hz电竞屏窄边框！",
          "brand" : "联想"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "2",
        "_score" : 1.0,
        "_source" : {
          "id" : 2,
          "title" : "Apple 2019款 Macbook Pro 13.3【带触控栏】八代i7 18G 256G RP645显卡 深空灰 苹果笔记本电脑 轻薄本 MUHN2CH/A",
          "price" : 15299,
          "intro" : "【八月精选】Pro2019年新品上市送三重好礼，现在购买领满8000减400元优惠神劵，劵后更优惠！",
          "brand" : "Apple"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "4",
        "_score" : 1.0,
        "_source" : {
          "id" : 4,
          "title" : "华为HUAWEI MateBook X Pro 2019款 英特尔酷睿i5 13.9英寸全面屏轻薄笔记本电脑(i5 8G 512G 3K 触控) 灰",
          "price" : 7999,
          "intro" : "3K全面屏开启无界视野;轻薄设计灵动有型，HuaweiShare一碰传",
          "brand" : "华为"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "6",
        "_score" : 1.0,
        "_source" : {
          "id" : 6,
          "title" : "华为平板 M6 10.8英寸麒麟980影音娱乐平板电脑4GB+64GB WiFi（香槟金）",
          "price" : 2299,
          "intro" : "【华为暑期购】8月2日-4日，M5青春版指定爆款型号优惠100元，AI语音控制",
          "brand" : "华为"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "1",
        "_score" : 1.0,
        "_source" : {
          "id" : 1,
          "title" : "Apple iPhone XR (A2108) 128GB 白色 移动联通电信4G手机 双卡双待",
          "price" : 5299,
          "intro" : "【iPhoneXR限时特惠！】6.1英寸视网膜显示屏，A12仿生芯片，面容识别，无线充电，支持双卡！选【换修无忧版】获 AppleCare 原厂服务，享只换不修！更有快速换机、保值换新、轻松月付！",
          "brand" : "Apple"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "7",
        "_score" : 1.0,
        "_source" : {
          "id" : 7,
          "title" : "荣耀20 PRO DXOMARK全球第二高分 4800万四摄 双光学防抖 麒麟980 全网通4G 8GB+128GB 蓝水翡翠 拍照手机",
          "price" : 3199,
          "intro" : "白条6期免息！麒麟980，4800万全焦段AI四摄！荣耀20系列2699起，4800万超广角AI四摄！",
          "brand" : "荣耀"
        }
      }
    ]
  }
}
```

## 结果排序

```json
GET /shop_product/shop_product/_search
{
  "sort": [
    {
      "price": "desc"
    }
  ]
}
{
  "took" : 314,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 12,
    "max_score" : null,
    "hits" : [
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "2",
        "_score" : null,
        "_source" : {
          "id" : 2,
          "title" : "Apple 2019款 Macbook Pro 13.3【带触控栏】八代i7 18G 256G RP645显卡 深空灰 苹果笔记本电脑 轻薄本 MUHN2CH/A",
          "price" : 15299,
          "intro" : "【八月精选】Pro2019年新品上市送三重好礼，现在购买领满8000减400元优惠神劵，劵后更优惠！",
          "brand" : "Apple"
        },
        "sort" : [
          15299
        ]
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "12",
        "_score" : null,
        "_source" : {
          "id" : 12,
          "title" : "联想(Lenovo)拯救者Y7000P 2019英特尔酷睿i7 15.6英寸游戏笔记本电脑(i7 9750H 16G 1T SSD GTX1660Ti 144Hz)",
          "price" : 9299,
          "intro" : "超大1T固态，升级双通道16G内存一步到位，GTX1660Ti电竞级独显，英特尔9代i7H高性能处理器，144Hz电竞屏窄边框！",
          "brand" : "联想"
        },
        "sort" : [
          9299
        ]
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "4",
        "_score" : null,
        "_source" : {
          "id" : 4,
          "title" : "华为HUAWEI MateBook X Pro 2019款 英特尔酷睿i5 13.9英寸全面屏轻薄笔记本电脑(i5 8G 512G 3K 触控) 灰",
          "price" : 7999,
          "intro" : "3K全面屏开启无界视野;轻薄设计灵动有型，HuaweiShare一碰传",
          "brand" : "华为"
        },
        "sort" : [
          7999
        ]
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "11",
        "_score" : null,
        "_source" : {
          "id" : 11,
          "title" : "小米(MI)Pro 2019款 15.6英寸金属轻薄笔记本(第八代英特尔酷睿i7-8550U 16G 512GSSD MX250 2G独显) 深空灰",
          "price" : 6899,
          "intro" : "【PCIE固态硬盘、72%NTSC高色域全高清屏】B面康宁玻璃覆盖、16G双通道大内存、第八代酷睿I7处理器、专业级调校MX150",
          "brand" : "小米"
        },
        "sort" : [
          6899
        ]
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "5",
        "_score" : null,
        "_source" : {
          "id" : 5,
          "title" : "华为 HUAWEI Mate20 X (5G) 7nm工艺5G旗舰芯片全面屏超大广角徕卡三摄8GB+256GB翡冷翠5G双模全网通手机",
          "price" : 6199,
          "intro" : "【5G双模，支持SA/NSA网络，7.2英寸全景巨屏，石墨烯液冷散热】5G先驱，极速体验。",
          "brand" : "华为"
        },
        "sort" : [
          6199
        ]
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "8",
        "_score" : null,
        "_source" : {
          "id" : 8,
          "title" : "荣耀MagicBook Pro 16.1英寸全面屏轻薄性能笔记本电脑（酷睿i7 8G 512G MX250 IPS FHD 指纹解锁）冰河银",
          "price" : 6199,
          "intro" : "16.1英寸无界全面屏金属轻薄本，100%sRGB色域，全高清IPS防眩光护眼屏，14小时长续航，指纹一健开机登录，魔法一碰传高速传输。",
          "brand" : "荣耀"
        },
        "sort" : [
          6199
        ]
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "1",
        "_score" : null,
        "_source" : {
          "id" : 1,
          "title" : "Apple iPhone XR (A2108) 128GB 白色 移动联通电信4G手机 双卡双待",
          "price" : 5299,
          "intro" : "【iPhoneXR限时特惠！】6.1英寸视网膜显示屏，A12仿生芯片，面容识别，无线充电，支持双卡！选【换修无忧版】获 AppleCare 原厂服务，享只换不修！更有快速换机、保值换新、轻松月付！",
          "brand" : "Apple"
        },
        "sort" : [
          5299
        ]
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "3",
        "_score" : null,
        "_source" : {
          "id" : 3,
          "title" : "Apple iPad Air 3 2019年新款平板电脑 10.5英寸（64G WLAN版/A12芯片/Retina显示屏/MUUL2CH/A）金色",
          "price" : 3788,
          "intro" : "8月尊享好礼！买iPad即送蓝牙耳机！领券立减！多款产品支持手写笔！【新一代iPad，总有一款适合你】选【换修无忧版】获 AppleCare 原厂服务，享只换不修！更有快速换机、保值换新、轻松月付！",
          "brand" : "Apple"
        },
        "sort" : [
          3788
        ]
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "7",
        "_score" : null,
        "_source" : {
          "id" : 7,
          "title" : "荣耀20 PRO DXOMARK全球第二高分 4800万四摄 双光学防抖 麒麟980 全网通4G 8GB+128GB 蓝水翡翠 拍照手机",
          "price" : 3199,
          "intro" : "白条6期免息！麒麟980，4800万全焦段AI四摄！荣耀20系列2699起，4800万超广角AI四摄！",
          "brand" : "荣耀"
        },
        "sort" : [
          3199
        ]
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "10",
        "_score" : null,
        "_source" : {
          "id" : 10,
          "title" : "小米9 4800万超广角三摄 6GB+128GB全息幻彩蓝 骁龙855 全网通4G 双卡双待 水滴全面屏拍照智能游戏手机",
          "price" : 2799,
          "intro" : "限时优惠200，成交价2799！索尼4800万广角微距三摄，屏下指纹解锁！",
          "brand" : "小米"
        },
        "sort" : [
          2799
        ]
      }
    ]
  }
}
```

## 分页查询

select \* from product limit 2, 5 2: 从第二条记录开始， 5： 取 5 条记录

```json
GET /shop_product/shop_product/_search
{
  "sort": [
    {
      "price": "desc"
    }
  ],
  "from": 0,
  "size": 3
}

{
  "took" : 2,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 12,
    "max_score" : null,
    "hits" : [
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "2",
        "_score" : null,
        "_source" : {
          "id" : 2,
          "title" : "Apple 2019款 Macbook Pro 13.3【带触控栏】八代i7 18G 256G RP645显卡 深空灰 苹果笔记本电脑 轻薄本 MUHN2CH/A",
          "price" : 15299,
          "intro" : "【八月精选】Pro2019年新品上市送三重好礼，现在购买领满8000减400元优惠神劵，劵后更优惠！",
          "brand" : "Apple"
        },
        "sort" : [
          15299
        ]
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "12",
        "_score" : null,
        "_source" : {
          "id" : 12,
          "title" : "联想(Lenovo)拯救者Y7000P 2019英特尔酷睿i7 15.6英寸游戏笔记本电脑(i7 9750H 16G 1T SSD GTX1660Ti 144Hz)",
          "price" : 9299,
          "intro" : "超大1T固态，升级双通道16G内存一步到位，GTX1660Ti电竞级独显，英特尔9代i7H高性能处理器，144Hz电竞屏窄边框！",
          "brand" : "联想"
        },
        "sort" : [
          9299
        ]
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "4",
        "_score" : null,
        "_source" : {
          "id" : 4,
          "title" : "华为HUAWEI MateBook X Pro 2019款 英特尔酷睿i5 13.9英寸全面屏轻薄笔记本电脑(i5 8G 512G 3K 触控) 灰",
          "price" : 7999,
          "intro" : "3K全面屏开启无界视野;轻薄设计灵动有型，HuaweiShare一碰传",
          "brand" : "华为"
        },
        "sort" : [
          7999
        ]
      }
    ]
  }
}
```

## 检索查询

需求 1：查询商品标题中符合“游戏 手机”的字样的商品

```json
GET /shop_product/shop_product/_search
{
  "query": {
    "term": {
      "title": "游戏 手机"
    }
  }
}

{
  "took" : 32,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 0,
    "max_score" : null,
    "hits" : [ ]
  }
}
```

需求 1：查询商品标题中符合“游戏 手机”的字样的商品

```json
GET /shop_product/shop_product/_search
{
  "query": {
    "match": {
      "title": "游戏 手机"
    }
  }
}

{
  "took" : 81,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 6,
    "max_score" : 1.3091815,
    "hits" : [
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "10",
        "_score" : 1.3091815,
        "_source" : {
          "id" : 10,
          "title" : "小米9 4800万超广角三摄 6GB+128GB全息幻彩蓝 骁龙855 全网通4G 双卡双待 水滴全面屏拍照智能游戏手机",
          "price" : 2799,
          "intro" : "限时优惠200，成交价2799！索尼4800万广角微距三摄，屏下指纹解锁！",
          "brand" : "小米"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "5",
        "_score" : 0.8374048,
        "_source" : {
          "id" : 5,
          "title" : "华为 HUAWEI Mate20 X (5G) 7nm工艺5G旗舰芯片全面屏超大广角徕卡三摄8GB+256GB翡冷翠5G双模全网通手机",
          "price" : 6199,
          "intro" : "【5G双模，支持SA/NSA网络，7.2英寸全景巨屏，石墨烯液冷散热】5G先驱，极速体验。",
          "brand" : "华为"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "12",
        "_score" : 0.5730595,
        "_source" : {
          "id" : 12,
          "title" : "联想(Lenovo)拯救者Y7000P 2019英特尔酷睿i7 15.6英寸游戏笔记本电脑(i7 9750H 16G 1T SSD GTX1660Ti 144Hz)",
          "price" : 9299,
          "intro" : "超大1T固态，升级双通道16G内存一步到位，GTX1660Ti电竞级独显，英特尔9代i7H高性能处理器，144Hz电竞屏窄边框！",
          "brand" : "联想"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "9",
        "_score" : 0.5525197,
        "_source" : {
          "id" : 9,
          "title" : "荣耀平板5 麒麟8核芯片 GT游戏加速 4G+128G 10.1英寸全高清屏影音平板电脑 WiFi版 冰川蓝",
          "price" : 1549,
          "intro" : "【爆款平板推荐】哈曼卡顿专业调音，10.1英寸全高清大屏，双喇叭立体环绕音，配置多重护眼，值得拥有！",
          "brand" : "荣耀"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "1",
        "_score" : 0.20132807,
        "_source" : {
          "id" : 1,
          "title" : "Apple iPhone XR (A2108) 128GB 白色 移动联通电信4G手机 双卡双待",
          "price" : 5299,
          "intro" : "【iPhoneXR限时特惠！】6.1英寸视网膜显示屏，A12仿生芯片，面容识别，无线充电，支持双卡！选【换修无忧版】获 AppleCare 原厂服务，享只换不修！更有快速换机、保值换新、轻松月付！",
          "brand" : "Apple"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "7",
        "_score" : 0.16659415,
        "_source" : {
          "id" : 7,
          "title" : "荣耀20 PRO DXOMARK全球第二高分 4800万四摄 双光学防抖 麒麟980 全网通4G 8GB+128GB 蓝水翡翠 拍照手机",
          "price" : 3199,
          "intro" : "白条6期免息！麒麟980，4800万全焦段AI四摄！荣耀20系列2699起，4800万超广角AI四摄！",
          "brand" : "荣耀"
        }
      }
    ]
  }
}
```

需求 2：查询商品价格等于 15299 的商品

```json
GET /shop_product/shop_product/_search
{
  "query": {
    "term": {
      "price": 15299
    }
  }
}

{
  "took" : 166,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 1,
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "2",
        "_score" : 1.0,
        "_source" : {
          "id" : 2,
          "title" : "Apple 2019款 Macbook Pro 13.3【带触控栏】八代i7 18G 256G RP645显卡 深空灰 苹果笔记本电脑 轻薄本 MUHN2CH/A",
          "price" : 15299,
          "intro" : "【八月精选】Pro2019年新品上市送三重好礼，现在购买领满8000减400元优惠神劵，劵后更优惠！",
          "brand" : "Apple"
        }
      }
    ]
  }
}
```

需求 3： 查询商品价格在 5000~10000 之间商品， 按照价格升序排列
gte: 大于等于 greater than equal
lte: less than equal

```json
GET /shop_product/shop_product/_search
{
  "query": {
    "range": {
      "price": {
        "gte": 5000,
        "lte": 10000
      }
    }
  }
}

{
  "took" : 46,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 6,
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "5",
        "_score" : 1.0,
        "_source" : {
          "id" : 5,
          "title" : "华为 HUAWEI Mate20 X (5G) 7nm工艺5G旗舰芯片全面屏超大广角徕卡三摄8GB+256GB翡冷翠5G双模全网通手机",
          "price" : 6199,
          "intro" : "【5G双模，支持SA/NSA网络，7.2英寸全景巨屏，石墨烯液冷散热】5G先驱，极速体验。",
          "brand" : "华为"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "8",
        "_score" : 1.0,
        "_source" : {
          "id" : 8,
          "title" : "荣耀MagicBook Pro 16.1英寸全面屏轻薄性能笔记本电脑（酷睿i7 8G 512G MX250 IPS FHD 指纹解锁）冰河银",
          "price" : 6199,
          "intro" : "16.1英寸无界全面屏金属轻薄本，100%sRGB色域，全高清IPS防眩光护眼屏，14小时长续航，指纹一健开机登录，魔法一碰传高速传输。",
          "brand" : "荣耀"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "12",
        "_score" : 1.0,
        "_source" : {
          "id" : 12,
          "title" : "联想(Lenovo)拯救者Y7000P 2019英特尔酷睿i7 15.6英寸游戏笔记本电脑(i7 9750H 16G 1T SSD GTX1660Ti 144Hz)",
          "price" : 9299,
          "intro" : "超大1T固态，升级双通道16G内存一步到位，GTX1660Ti电竞级独显，英特尔9代i7H高性能处理器，144Hz电竞屏窄边框！",
          "brand" : "联想"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "4",
        "_score" : 1.0,
        "_source" : {
          "id" : 4,
          "title" : "华为HUAWEI MateBook X Pro 2019款 英特尔酷睿i5 13.9英寸全面屏轻薄笔记本电脑(i5 8G 512G 3K 触控) 灰",
          "price" : 7999,
          "intro" : "3K全面屏开启无界视野;轻薄设计灵动有型，HuaweiShare一碰传",
          "brand" : "华为"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "1",
        "_score" : 1.0,
        "_source" : {
          "id" : 1,
          "title" : "Apple iPhone XR (A2108) 128GB 白色 移动联通电信4G手机 双卡双待",
          "price" : 5299,
          "intro" : "【iPhoneXR限时特惠！】6.1英寸视网膜显示屏，A12仿生芯片，面容识别，无线充电，支持双卡！选【换修无忧版】获 AppleCare 原厂服务，享只换不修！更有快速换机、保值换新、轻松月付！",
          "brand" : "Apple"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "11",
        "_score" : 1.0,
        "_source" : {
          "id" : 11,
          "title" : "小米(MI)Pro 2019款 15.6英寸金属轻薄笔记本(第八代英特尔酷睿i7-8550U 16G 512GSSD MX250 2G独显) 深空灰",
          "price" : 6899,
          "intro" : "【PCIE固态硬盘、72%NTSC高色域全高清屏】B面康宁玻璃覆盖、16G双通道大内存、第八代酷睿I7处理器、专业级调校MX150",
          "brand" : "小米"
        }
      }
    ]
  }
}
```

## 关键字查询

需求 1： 查询商品标题或简介中符合“蓝牙 指纹 双卡”的字样商品

```json
GET /shop_product/shop_product/_search
{
  "query": {
    "multi_match": {
      "query": "蓝牙 指纹 双卡",
      "fields": ["title", "intro"]
    }
  }
}

{
  "took" : 169,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 6,
    "max_score" : 3.0893953,
    "hits" : [
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "10",
        "_score" : 3.0893953,
        "_source" : {
          "id" : 10,
          "title" : "小米9 4800万超广角三摄 6GB+128GB全息幻彩蓝 骁龙855 全网通4G 双卡双待 水滴全面屏拍照智能游戏手机",
          "price" : 2799,
          "intro" : "限时优惠200，成交价2799！索尼4800万广角微距三摄，屏下指纹解锁！",
          "brand" : "小米"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "9",
        "_score" : 2.6872945,
        "_source" : {
          "id" : 9,
          "title" : "荣耀平板5 麒麟8核芯片 GT游戏加速 4G+128G 10.1英寸全高清屏影音平板电脑 WiFi版 冰川蓝",
          "price" : 1549,
          "intro" : "【爆款平板推荐】哈曼卡顿专业调音，10.1英寸全高清大屏，双喇叭立体环绕音，配置多重护眼，值得拥有！",
          "brand" : "荣耀"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "8",
        "_score" : 1.447008,
        "_source" : {
          "id" : 8,
          "title" : "荣耀MagicBook Pro 16.1英寸全面屏轻薄性能笔记本电脑（酷睿i7 8G 512G MX250 IPS FHD 指纹解锁）冰河银",
          "price" : 6199,
          "intro" : "16.1英寸无界全面屏金属轻薄本，100%sRGB色域，全高清IPS防眩光护眼屏，14小时长续航，指纹一健开机登录，魔法一碰传高速传输。",
          "brand" : "荣耀"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "1",
        "_score" : 1.2347561,
        "_source" : {
          "id" : 1,
          "title" : "Apple iPhone XR (A2108) 128GB 白色 移动联通电信4G手机 双卡双待",
          "price" : 5299,
          "intro" : "【iPhoneXR限时特惠！】6.1英寸视网膜显示屏，A12仿生芯片，面容识别，无线充电，支持双卡！选【换修无忧版】获 AppleCare 原厂服务，享只换不修！更有快速换机、保值换新、轻松月付！",
          "brand" : "Apple"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "3",
        "_score" : 0.63818395,
        "_source" : {
          "id" : 3,
          "title" : "Apple iPad Air 3 2019年新款平板电脑 10.5英寸（64G WLAN版/A12芯片/Retina显示屏/MUUL2CH/A）金色",
          "price" : 3788,
          "intro" : "8月尊享好礼！买iPad即送蓝牙耳机！领券立减！多款产品支持手写笔！【新一代iPad，总有一款适合你】选【换修无忧版】获 AppleCare 原厂服务，享只换不修！更有快速换机、保值换新、轻松月付！",
          "brand" : "Apple"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "7",
        "_score" : 0.16659415,
        "_source" : {
          "id" : 7,
          "title" : "荣耀20 PRO DXOMARK全球第二高分 4800万四摄 双光学防抖 麒麟980 全网通4G 8GB+128GB 蓝水翡翠 拍照手机",
          "price" : 3199,
          "intro" : "白条6期免息！麒麟980，4800万全焦段AI四摄！荣耀20系列2699起，4800万超广角AI四摄！",
          "brand" : "荣耀"
        }
      }
    ]
  }
}
```

## 高亮显示

```json
GET /shop_product/shop_product/_search
{
  "query": {
    "multi_match": {
      "query": "蓝牙 指纹 双卡",
      "fields": ["title", "intro"]
    }
  },
  "highlight": {
    "fields": {
      "title": {},
      "intro": {}
    }
  }
}

{
  "took" : 448,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 6,
    "max_score" : 3.0893953,
    "hits" : [
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "10",
        "_score" : 3.0893953,
        "_source" : {
          "id" : 10,
          "title" : "小米9 4800万超广角三摄 6GB+128GB全息幻彩蓝 骁龙855 全网通4G 双卡双待 水滴全面屏拍照智能游戏手机",
          "price" : 2799,
          "intro" : "限时优惠200，成交价2799！索尼4800万广角微距三摄，屏下指纹解锁！",
          "brand" : "小米"
        },
        "highlight" : {
          "intro" : [
            "索尼4800万广角微距三摄，屏下<em>指纹</em>解锁！"
          ],
          "title" : [
            "小米9 4800万超广角三摄 6GB+128GB全息幻彩蓝 骁龙855 全网通4G <em>双</em><em>卡</em><em>双</em>待 水滴全面屏拍照智能游戏手机"
          ]
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "9",
        "_score" : 2.6872945,
        "_source" : {
          "id" : 9,
          "title" : "荣耀平板5 麒麟8核芯片 GT游戏加速 4G+128G 10.1英寸全高清屏影音平板电脑 WiFi版 冰川蓝",
          "price" : 1549,
          "intro" : "【爆款平板推荐】哈曼卡顿专业调音，10.1英寸全高清大屏，双喇叭立体环绕音，配置多重护眼，值得拥有！",
          "brand" : "荣耀"
        },
        "highlight" : {
          "intro" : [
            "【爆款平板推荐】哈曼<em>卡</em>顿专业调音，10.1英寸全高清大屏，<em>双</em>喇叭立体环绕音，配置多重护眼，值得拥有！"
          ]
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "8",
        "_score" : 1.447008,
        "_source" : {
          "id" : 8,
          "title" : "荣耀MagicBook Pro 16.1英寸全面屏轻薄性能笔记本电脑（酷睿i7 8G 512G MX250 IPS FHD 指纹解锁）冰河银",
          "price" : 6199,
          "intro" : "16.1英寸无界全面屏金属轻薄本，100%sRGB色域，全高清IPS防眩光护眼屏，14小时长续航，指纹一健开机登录，魔法一碰传高速传输。",
          "brand" : "荣耀"
        },
        "highlight" : {
          "intro" : [
            "16.1英寸无界全面屏金属轻薄本，100%sRGB色域，全高清IPS防眩光护眼屏，14小时长续航，<em>指纹</em>一健开机登录，魔法一碰传高速传输。"
          ],
          "title" : [
            "荣耀MagicBook Pro 16.1英寸全面屏轻薄性能笔记本电脑（酷睿i7 8G 512G MX250 IPS FHD <em>指纹</em>解锁）冰河银"
          ]
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "1",
        "_score" : 1.2347561,
        "_source" : {
          "id" : 1,
          "title" : "Apple iPhone XR (A2108) 128GB 白色 移动联通电信4G手机 双卡双待",
          "price" : 5299,
          "intro" : "【iPhoneXR限时特惠！】6.1英寸视网膜显示屏，A12仿生芯片，面容识别，无线充电，支持双卡！选【换修无忧版】获 AppleCare 原厂服务，享只换不修！更有快速换机、保值换新、轻松月付！",
          "brand" : "Apple"
        },
        "highlight" : {
          "intro" : [
            "6.1英寸视网膜显示屏，A12仿生芯片，面容识别，无线充电，支持<em>双</em><em>卡</em>！选【换修无忧版】获 AppleCare 原厂服务，享只换不修！更有快速换机、保值换新、轻松月付！"
          ],
          "title" : [
            "Apple iPhone XR (A2108) 128GB 白色 移动联通电信4G手机 <em>双</em><em>卡</em><em>双</em>待"
          ]
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "3",
        "_score" : 0.63818395,
        "_source" : {
          "id" : 3,
          "title" : "Apple iPad Air 3 2019年新款平板电脑 10.5英寸（64G WLAN版/A12芯片/Retina显示屏/MUUL2CH/A）金色",
          "price" : 3788,
          "intro" : "8月尊享好礼！买iPad即送蓝牙耳机！领券立减！多款产品支持手写笔！【新一代iPad，总有一款适合你】选【换修无忧版】获 AppleCare 原厂服务，享只换不修！更有快速换机、保值换新、轻松月付！",
          "brand" : "Apple"
        },
        "highlight" : {
          "intro" : [
            "买iPad即送<em>蓝牙</em>耳机！领券立减！多款产品支持手写笔！【新一代iPad，总有一款适合你】选【换修无忧版】获 AppleCare 原厂服务，享只换不修！更有快速换机、保值换新、轻松月付！"
          ]
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "7",
        "_score" : 0.16659415,
        "_source" : {
          "id" : 7,
          "title" : "荣耀20 PRO DXOMARK全球第二高分 4800万四摄 双光学防抖 麒麟980 全网通4G 8GB+128GB 蓝水翡翠 拍照手机",
          "price" : 3199,
          "intro" : "白条6期免息！麒麟980，4800万全焦段AI四摄！荣耀20系列2699起，4800万超广角AI四摄！",
          "brand" : "荣耀"
        },
        "highlight" : {
          "title" : [
            "荣耀20 PRO DXOMARK全球第二高分 4800万四摄 <em>双</em>光学防抖 麒麟980 全网通4G 8GB+128GB 蓝水翡翠 拍照手机"
          ]
        }
      }
    ]
  }
}

GET /shop_product/shop_product/_search
{
  "query": {
    "multi_match": {
      "query": "蓝牙 指纹 双卡",
      "fields": ["title", "intro"]
    }
  },
  "highlight": {
    "fields": {
      "title": {},
      "intro": {}
    },
    "pre_tags": "<span style='color:red'>",
    "post_tags": "</span>"
  }
}

{
  "took" : 197,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 6,
    "max_score" : 3.0893953,
    "hits" : [
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "10",
        "_score" : 3.0893953,
        "_source" : {
          "id" : 10,
          "title" : "小米9 4800万超广角三摄 6GB+128GB全息幻彩蓝 骁龙855 全网通4G 双卡双待 水滴全面屏拍照智能游戏手机",
          "price" : 2799,
          "intro" : "限时优惠200，成交价2799！索尼4800万广角微距三摄，屏下指纹解锁！",
          "brand" : "小米"
        },
        "highlight" : {
          "intro" : [
            "索尼4800万广角微距三摄，屏下<span style='color:red'>指纹</span>解锁！"
          ],
          "title" : [
            "小米9 4800万超广角三摄 6GB+128GB全息幻彩蓝 骁龙855 全网通4G <span style='color:red'>双</span><span style='color:red'>卡</span><span style='color:red'>双</span>待 水滴全面屏拍照智能游戏手机"
          ]
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "9",
        "_score" : 2.6872945,
        "_source" : {
          "id" : 9,
          "title" : "荣耀平板5 麒麟8核芯片 GT游戏加速 4G+128G 10.1英寸全高清屏影音平板电脑 WiFi版 冰川蓝",
          "price" : 1549,
          "intro" : "【爆款平板推荐】哈曼卡顿专业调音，10.1英寸全高清大屏，双喇叭立体环绕音，配置多重护眼，值得拥有！",
          "brand" : "荣耀"
        },
        "highlight" : {
          "intro" : [
            "【爆款平板推荐】哈曼<span style='color:red'>卡</span>顿专业调音，10.1英寸全高清大屏，<span style='color:red'>双</span>喇叭立体环绕音，配置多重护眼，值得拥有！"
          ]
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "8",
        "_score" : 1.447008,
        "_source" : {
          "id" : 8,
          "title" : "荣耀MagicBook Pro 16.1英寸全面屏轻薄性能笔记本电脑（酷睿i7 8G 512G MX250 IPS FHD 指纹解锁）冰河银",
          "price" : 6199,
          "intro" : "16.1英寸无界全面屏金属轻薄本，100%sRGB色域，全高清IPS防眩光护眼屏，14小时长续航，指纹一健开机登录，魔法一碰传高速传输。",
          "brand" : "荣耀"
        },
        "highlight" : {
          "intro" : [
            "16.1英寸无界全面屏金属轻薄本，100%sRGB色域，全高清IPS防眩光护眼屏，14小时长续航，<span style='color:red'>指纹</span>一健开机登录，魔法一碰传高速传输。"
          ],
          "title" : [
            "荣耀MagicBook Pro 16.1英寸全面屏轻薄性能笔记本电脑（酷睿i7 8G 512G MX250 IPS FHD <span style='color:red'>指纹</span>解锁）冰河银"
          ]
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "1",
        "_score" : 1.2347561,
        "_source" : {
          "id" : 1,
          "title" : "Apple iPhone XR (A2108) 128GB 白色 移动联通电信4G手机 双卡双待",
          "price" : 5299,
          "intro" : "【iPhoneXR限时特惠！】6.1英寸视网膜显示屏，A12仿生芯片，面容识别，无线充电，支持双卡！选【换修无忧版】获 AppleCare 原厂服务，享只换不修！更有快速换机、保值换新、轻松月付！",
          "brand" : "Apple"
        },
        "highlight" : {
          "intro" : [
            "6.1英寸视网膜显示屏，A12仿生芯片，面容识别，无线充电，支持<span style='color:red'>双</span><span style='color:red'>卡</span>！选【换修无忧版】获 AppleCare 原厂服务，享只换不修！更有快速换机、保值换新、轻松月付！"
          ],
          "title" : [
            "Apple iPhone XR (A2108) 128GB 白色 移动联通电信4G手机 <span style='color:red'>双</span><span style='color:red'>卡</span><span style='color:red'>双</span>待"
          ]
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "3",
        "_score" : 0.63818395,
        "_source" : {
          "id" : 3,
          "title" : "Apple iPad Air 3 2019年新款平板电脑 10.5英寸（64G WLAN版/A12芯片/Retina显示屏/MUUL2CH/A）金色",
          "price" : 3788,
          "intro" : "8月尊享好礼！买iPad即送蓝牙耳机！领券立减！多款产品支持手写笔！【新一代iPad，总有一款适合你】选【换修无忧版】获 AppleCare 原厂服务，享只换不修！更有快速换机、保值换新、轻松月付！",
          "brand" : "Apple"
        },
        "highlight" : {
          "intro" : [
            "买iPad即送<span style='color:red'>蓝牙</span>耳机！领券立减！多款产品支持手写笔！【新一代iPad，总有一款适合你】选【换修无忧版】获 AppleCare 原厂服务，享只换不修！更有快速换机、保值换新、轻松月付！"
          ]
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "7",
        "_score" : 0.16659415,
        "_source" : {
          "id" : 7,
          "title" : "荣耀20 PRO DXOMARK全球第二高分 4800万四摄 双光学防抖 麒麟980 全网通4G 8GB+128GB 蓝水翡翠 拍照手机",
          "price" : 3199,
          "intro" : "白条6期免息！麒麟980，4800万全焦段AI四摄！荣耀20系列2699起，4800万超广角AI四摄！",
          "brand" : "荣耀"
        },
        "highlight" : {
          "title" : [
            "荣耀20 PRO DXOMARK全球第二高分 4800万四摄 <span style='color:red'>双</span>光学防抖 麒麟980 全网通4G 8GB+128GB 蓝水翡翠 拍照手机"
          ]
        }
      }
    ]
  }
}
```

## 逻辑查询

需求 1：查询商品标题中符合“i7”的字样并且价格大于 7000 的商品

```json
GET /shop_product/shop_product/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "title": "i7"
          }
        },
        {
          "range": {
            "price": {
              "gte": 7000
            }
          }
        }
      ]
    }
  }
}

{
  "took" : 258,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 2,
    "max_score" : 2.2550578,
    "hits" : [
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "12",
        "_score" : 2.2550578,
        "_source" : {
          "id" : 12,
          "title" : "联想(Lenovo)拯救者Y7000P 2019英特尔酷睿i7 15.6英寸游戏笔记本电脑(i7 9750H 16G 1T SSD GTX1660Ti 144Hz)",
          "price" : 9299,
          "intro" : "超大1T固态，升级双通道16G内存一步到位，GTX1660Ti电竞级独显，英特尔9代i7H高性能处理器，144Hz电竞屏窄边框！",
          "brand" : "联想"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "2",
        "_score" : 1.9102095,
        "_source" : {
          "id" : 2,
          "title" : "Apple 2019款 Macbook Pro 13.3【带触控栏】八代i7 18G 256G RP645显卡 深空灰 苹果笔记本电脑 轻薄本 MUHN2CH/A",
          "price" : 15299,
          "intro" : "【八月精选】Pro2019年新品上市送三重好礼，现在购买领满8000减400元优惠神劵，劵后更优惠！",
          "brand" : "Apple"
        }
      }
    ]
  }
}
```

需求 2：查询商品标题中符合“pro”的字样或者价格在 1000~3000 的商品

```json
GET /shop_product/shop_product/_search
{
  "query": {
    "bool": {
      "should": [
        {
          "match": {
            "title": "pro"
          }
        },
        {
          "range": {
            "price": {
              "gte": 1000,
              "lte": 3000
            }
          }
        }
      ]
    }
  }
}

{
  "took" : 40,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 8,
    "max_score" : 1.447008,
    "hits" : [
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "8",
        "_score" : 1.447008,
        "_source" : {
          "id" : 8,
          "title" : "荣耀MagicBook Pro 16.1英寸全面屏轻薄性能笔记本电脑（酷睿i7 8G 512G MX250 IPS FHD 指纹解锁）冰河银",
          "price" : 6199,
          "intro" : "16.1英寸无界全面屏金属轻薄本，100%sRGB色域，全高清IPS防眩光护眼屏，14小时长续航，指纹一健开机登录，魔法一碰传高速传输。",
          "brand" : "荣耀"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "9",
        "_score" : 1.0,
        "_source" : {
          "id" : 9,
          "title" : "荣耀平板5 麒麟8核芯片 GT游戏加速 4G+128G 10.1英寸全高清屏影音平板电脑 WiFi版 冰川蓝",
          "price" : 1549,
          "intro" : "【爆款平板推荐】哈曼卡顿专业调音，10.1英寸全高清大屏，双喇叭立体环绕音，配置多重护眼，值得拥有！",
          "brand" : "荣耀"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "10",
        "_score" : 1.0,
        "_source" : {
          "id" : 10,
          "title" : "小米9 4800万超广角三摄 6GB+128GB全息幻彩蓝 骁龙855 全网通4G 双卡双待 水滴全面屏拍照智能游戏手机",
          "price" : 2799,
          "intro" : "限时优惠200，成交价2799！索尼4800万广角微距三摄，屏下指纹解锁！",
          "brand" : "小米"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "6",
        "_score" : 1.0,
        "_source" : {
          "id" : 6,
          "title" : "华为平板 M6 10.8英寸麒麟980影音娱乐平板电脑4GB+64GB WiFi（香槟金）",
          "price" : 2299,
          "intro" : "【华为暑期购】8月2日-4日，M5青春版指定爆款型号优惠100元，AI语音控制",
          "brand" : "华为"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "11",
        "_score" : 0.6602099,
        "_source" : {
          "id" : 11,
          "title" : "小米(MI)Pro 2019款 15.6英寸金属轻薄笔记本(第八代英特尔酷睿i7-8550U 16G 512GSSD MX250 2G独显) 深空灰",
          "price" : 6899,
          "intro" : "【PCIE固态硬盘、72%NTSC高色域全高清屏】B面康宁玻璃覆盖、16G双通道大内存、第八代酷睿I7处理器、专业级调校MX150",
          "brand" : "小米"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "7",
        "_score" : 0.6333549,
        "_source" : {
          "id" : 7,
          "title" : "荣耀20 PRO DXOMARK全球第二高分 4800万四摄 双光学防抖 麒麟980 全网通4G 8GB+128GB 蓝水翡翠 拍照手机",
          "price" : 3199,
          "intro" : "白条6期免息！麒麟980，4800万全焦段AI四摄！荣耀20系列2699起，4800万超广角AI四摄！",
          "brand" : "荣耀"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "4",
        "_score" : 0.4448996,
        "_source" : {
          "id" : 4,
          "title" : "华为HUAWEI MateBook X Pro 2019款 英特尔酷睿i5 13.9英寸全面屏轻薄笔记本电脑(i5 8G 512G 3K 触控) 灰",
          "price" : 7999,
          "intro" : "3K全面屏开启无界视野;轻薄设计灵动有型，HuaweiShare一碰传",
          "brand" : "华为"
        }
      },
      {
        "_index" : "shop_product",
        "_type" : "shop_product",
        "_id" : "2",
        "_score" : 0.4361634,
        "_source" : {
          "id" : 2,
          "title" : "Apple 2019款 Macbook Pro 13.3【带触控栏】八代i7 18G 256G RP645显卡 深空灰 苹果笔记本电脑 轻薄本 MUHN2CH/A",
          "price" : 15299,
          "intro" : "【八月精选】Pro2019年新品上市送三重好礼，现在购买领满8000减400元优惠神劵，劵后更优惠！",
          "brand" : "Apple"
        }
      }
    ]
  }
}
```

## SQL 操作语法

http 操作

```json
POST /_xpack/sql?format=txt
{
  "query": "select id, brand, price from shop_product"
}

      id       |     brand     |     price
---------------+---------------+---------------
5              |华为             |6199
2              |Apple          |15299
1              |Apple          |5299
3              |Apple          |3788
8              |荣耀             |6199
4              |华为             |7999
7              |荣耀             |3199
11             |小米             |6899
9              |荣耀             |1549
6              |华为             |2299
10             |小米             |2799
12             |联想             |9299

POST /_xpack/sql?format=json
{
  "query": "select id, brand, price from shop_product"
}

{"columns":[{"name":"id","type":"long"},{"name":"brand","type":"keyword"},{"name":"price","type":"integer"}],"rows":[[5,"华为",6199],[2,"Apple",15299],[1,"Apple",5299],[3,"Apple",3788],[8,"荣耀",6199],[4,"华为",7999],[7,"荣耀",3199],[11,"小米",6899],[9,"荣耀",1549],[6,"华为",2299],[10,"小米",2799],[12,"联想",9299]]}

POST /_xpack/sql?format=txt
{
  "query": "select id, brand, price from shop_product where title like '游戏 手机'"
}

      id       |     brand     |     price
---------------+---------------+---------------
5              |华为             |6199
1              |Apple          |5299
7              |荣耀             |3199
9              |荣耀             |1549
10             |小米             |2799
12             |联想             |9299

```

创建一个索引库

```json
PUT /test

#! Deprecation: the default number of shards will change from [5] to [1] in 7.0.0; if you wish to continue using the default of [5] shards, you must manage this on the create index request or with an index template
{
  "acknowledged" : true,
  "shards_acknowledged" : true,
  "index" : "test"
}

PUT /test/_doc/1
{
  "id": 1,
  "hobbys": ["篮球", "排球"]
}

{
  "_index" : "test",
  "_type" : "_doc",
  "_id" : "1",
  "_version" : 1,
  "result" : "created",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 0,
  "_primary_term" : 1
}

GET /test/_doc/1

{
  "_index" : "test",
  "_type" : "_doc",
  "_id" : "1",
  "_version" : 9,
  "found" : true,
  "_source" : {
    "id" : 1,
    "hobbys" : [
      "篮球",
      "排球"
    ]
  }
}

PUT /test/_doc/2
{
  "id": 2,
  "hobbys": "乒乓球"
}

{
  "_index" : "test",
  "_type" : "_doc",
  "_id" : "2",
  "_version" : 1,
  "result" : "created",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 0,
  "_primary_term" : 1
}

GET /test/_doc/_search

{
  "took" : 21,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 2,
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "test",
        "_type" : "_doc",
        "_id" : "2",
        "_score" : 1.0,
        "_source" : {
          "id" : 2,
          "hobbys" : "乒乓球"
        }
      },
      {
        "_index" : "test",
        "_type" : "_doc",
        "_id" : "1",
        "_score" : 1.0,
        "_source" : {
          "id" : 1,
          "hobbys" : [
            "篮球",
            "排球"
          ]
        }
      }
    ]
  }
}

GET /_xpack/sql?format=txt
{
  "query": "select * from test where id = 2"
}

    hobbys     |      id
---------------+---------------
乒乓球            |2

GET /_xpack/sql?format=txt
{
  "query": "select * from test where id = 1"
}

{
  "error": {
    "root_cause": [
      {
        "type": "sql_illegal_argument_exception",
        "reason": "Arrays (returned by [hobbys]) are not supported"
      }
    ],
    "type": "sql_illegal_argument_exception",
    "reason": "Arrays (returned by [hobbys]) are not supported"
  },
  "status": 500
}
```

问题: 把 sql 转化为 dsl 语句

```json
GET /_xpack/sql/translate
{
  "query": "select * from test where id = 1"
}

{
  "size" : 1000,
  "query" : {
    "term" : {
      "id" : {
        "value" : 1,
        "boost" : 1.0
      }
    }
  },
  "_source" : {
    "includes" : [
      "hobbys"
    ],
    "excludes" : [ ]
  },
  "docvalue_fields" : [
    {
      "field" : "id",
      "format" : "use_field_mapping"
    }
  ],
  "sort" : [
    {
      "_doc" : {
        "order" : "asc"
      }
    }
  ]
}

GET /test/_doc/_search
{
  "size" : 1000,
  "query" : {
    "term" : {
      "id" : {
        "value" : 1,
        "boost" : 1.0
      }
    }
  },
  "_source" : {
    "includes" : [
      "hobbys"
    ],
    "excludes" : [ ]
  },
  "docvalue_fields" : [
    {
      "field" : "id",
      "format" : "use_field_mapping"
    }
  ],
  "sort" : [
    {
      "_doc" : {
        "order" : "asc"
      }
    }
  ]
}

{
  "took" : 122,
  "timed_out" : false,
  "_shards" : {
    "total" : 5,
    "successful" : 5,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : 1,
    "max_score" : null,
    "hits" : [
      {
        "_index" : "test",
        "_type" : "_doc",
        "_id" : "1",
        "_score" : null,
        "_source" : {
          "hobbys" : [
            "篮球",
            "排球"
          ]
        },
        "fields" : {
          "id" : [
            1
          ]
        },
        "sort" : [
          0
        ]
      }
    ]
  }
}
```

常用命令 SQL Commands

```json
GET /_xpack/sql?format=txt
{
  "query": "show tables"
}

     name      |     type
---------------+---------------
.kibana        |ALIAS
.kibana_1      |BASE TABLE
shop_product   |BASE TABLE
test           |BASE TABLE

GET /_xpack/sql?format=txt
{
  "query": "desc test"
}

    column     |     type      |    mapping
---------------+---------------+---------------
hobbys         |VARCHAR        |TEXT
hobbys.keyword |VARCHAR        |KEYWORD
id             |BIGINT         |LONG

GET /_xpack/sql?format=txt
{
  "query": "show columns in test"
}

    column     |     type      |    mapping
---------------+---------------+---------------
hobbys         |VARCHAR        |TEXT
hobbys.keyword |VARCHAR        |KEYWORD
id             |BIGINT         |LONG

GET /_xpack/sql?format=txt
{
  "query": "select id, price, brand from shop_product"
}

      id       |     price     |     brand
---------------+---------------+---------------
5              |6199           |华为
2              |15299          |Apple
1              |5299           |Apple
3              |3788           |Apple
8              |6199           |荣耀
4              |7999           |华为
7              |3199           |荣耀
11             |6899           |小米
9              |1549           |荣耀
6              |2299           |华为
10             |2799           |小米
12             |9299           |联想

GET /_xpack/sql?format=txt
{
  "query": "select id, price, brand from shop_product limit 5"
}

      id       |     price     |     brand
---------------+---------------+---------------
5              |6199           |华为
2              |15299          |Apple
1              |5299           |Apple
3              |3788           |Apple
8              |6199           |荣耀

GET /_xpack/sql?format=txt
{
  "query": "select brand, avg(price) from shop_product group by brand"
}

     brand     |   AVG(price)
---------------+-----------------
Apple          |8128.666666666667
华为             |5499.0
小米             |4849.0
联想             |9299.0
荣耀             |3649.0

GET /_xpack/sql?format=txt
{
  "query": "select brand, avg(price), count(*) from shop_product group by brand"
}

     brand     |   AVG(price)    |   COUNT(1)
---------------+-----------------+---------------
Apple          |8128.666666666667|3
华为             |5499.0           |3
小米             |4849.0           |2
联想             |9299.0           |1
荣耀             |3649.0           |3

GET /_xpack/sql?format=txt
{
  "query": "select brand, avg(price) avgPrice, count(*) from shop_product group by brand having avgPrice > 5000"
}

     brand     |    avgPrice     |   COUNT(1)
---------------+-----------------+---------------
Apple          |8128.666666666667|3
华为             |5499.0           |3
联想             |9299.0           |1

GET /_xpack/sql?format=txt
{
  "query": "select brand, avg(price) avgPrice, count(*), max(price), min(price) from shop_product group by brand having avgPrice > 5000"
}

     brand     |    avgPrice     |   COUNT(1)    |  MAX(price)   |  MIN(price)
---------------+-----------------+---------------+---------------+---------------
Apple          |8128.666666666667|3              |15299.0        |3788.0
华为             |5499.0           |3              |7999.0         |2299.0
联想             |9299.0           |1              |9299.0         |9299.0
```

## 节点的状态信息

```json
GET /_cat/master?v

id                     host          ip            node
9_-cLjLXQPeySWP2wRlXvA 172.16.23.125 172.16.23.125 lab001

GET /_cat/nodes?v
ip            heap.percent ram.percent cpu load_1m load_5m load_15m node.role master name
172.16.23.125           15          94  11    0.02    0.39     0.61 mdi       *      lab001
```

## 集群的健康状态

```json
GET /_cat/health?v

epoch      timestamp cluster status node.total node.data shards pri relo init unassign pending_tasks max_task_wait_time active_shards_percent
1614317354 05:29:14  my-es   yellow          1         1     11  11    0    0       10             0                  -                 52.4%
```
