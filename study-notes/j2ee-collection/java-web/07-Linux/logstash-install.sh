# 解压tar包
$tar -zxvf logstash-6.5.4.tar.gz -C /usr/local/

# 进入目录
$cd /usr/local/logstash-6.5.4

# 启动logstash
$bin/logstash -e 'input { stdin {} } output { stdout {} }'
Sending Logstash logs to /usr/local/logstash-6.5.4/logs which is now configured via log4j2.properties
[2021-03-01T03:32:06,251][INFO ][logstash.setting.writabledirectory] Creating directory {:setting=>"path.queue", :path=>"/usr/local/logstash-6.5.4/data/queue"}
[2021-03-01T03:32:06,294][INFO ][logstash.setting.writabledirectory] Creating directory {:setting=>"path.dead_letter_queue", :path=>"/usr/local/logstash-6.5.4/data/dead_letter_queue"}
[2021-03-01T03:32:08,658][WARN ][logstash.config.source.multilocal] Ignoring the 'pipelines.yml' file because modules or command line options are specified
[2021-03-01T03:32:08,731][INFO ][logstash.runner          ] Starting Logstash {"logstash.version"=>"6.5.4"}
[2021-03-01T03:32:08,900][INFO ][logstash.agent           ] No persistent UUID file found. Generating new UUID {:uuid=>"d01f6244-ac7a-40c5-800f-b8531cd319d6", :path=>"/usr/local/logstash-6.5.4/data/uuid"}
[2021-03-01T03:32:25,164][INFO ][logstash.pipeline        ] Starting pipeline {:pipeline_id=>"main", "pipeline.workers"=>1, "pipeline.batch.size"=>125, "pipeline.batch.delay"=>50}
[2021-03-01T03:32:25,926][INFO ][logstash.pipeline        ] Pipeline started successfully {:pipeline_id=>"main", :thread=>"#<Thread:0xba2d3e5 run>"}
The stdin plugin is now waiting for input:
[2021-03-01T03:32:26,301][INFO ][logstash.agent           ] Pipelines running {:count=>1, :running_pipelines=>[:main], :non_running_pipelines=>[]}
[2021-03-01T03:32:27,542][INFO ][logstash.agent           ] Successfully started Logstash API endpoint {:port=>9600}
hehe
{
    "@timestamp" => 2021-03-01T08:33:46.666Z,
      "@version" => "1",
          "host" => "localhost.localdomain",
       "message" => "hehe"
}
hello
{
    "@timestamp" => 2021-03-01T08:33:57.854Z,
      "@version" => "1",
          "host" => "localhost.localdomain",
       "message" => "hello"
}

# 读取文本文件
$pwd
/root
$vi test.log
error 500 http
error 500 http
error 500 http
error 500 http
error 500 http
error 500 http
error 500 http
error 500 http
error 500 http

$pwd
/usr/local/logstash-6.5.4
$mkdir etc
$vi etc/file.conf
input {
  file {
    path => "/root/test.log"
    start_position => "beginning"
    stat_interval => "2"
  }
}
output {
  stdout { codec => rubydebug }
}

# 启动通过配置文件的方式
$bin/logstash -f etc/file.conf
{
      "@version" => "1",
       "message" => "error 500 http",
          "path" => "/root/test.log",
          "host" => "localhost.localdomain",
    "@timestamp" => 2021-03-01T08:50:49.679Z
}
{
      "@version" => "1",
       "message" => "error 500 http",
          "path" => "/root/test.log",
          "host" => "localhost.localdomain",
    "@timestamp" => 2021-03-01T08:50:49.737Z
}
{
      "@version" => "1",
       "message" => "error 500 http",
          "path" => "/root/test.log",
          "host" => "localhost.localdomain",
    "@timestamp" => 2021-03-01T08:50:49.738Z
}
{
      "@version" => "1",
       "message" => "error 500 http",
          "path" => "/root/test.log",
          "host" => "localhost.localdomain",
    "@timestamp" => 2021-03-01T08:50:49.738Z
}
{
      "@version" => "1",
       "message" => "error 500 http",
          "path" => "/root/test.log",
          "host" => "localhost.localdomain",
    "@timestamp" => 2021-03-01T08:50:49.738Z
}
{
      "@version" => "1",
       "message" => "error 500 http",
          "path" => "/root/test.log",
          "host" => "localhost.localdomain",
    "@timestamp" => 2021-03-01T08:50:49.738Z
}
{
      "@version" => "1",
       "message" => "error 500 http",
          "path" => "/root/test.log",
          "host" => "localhost.localdomain",
    "@timestamp" => 2021-03-01T08:50:49.739Z
}
{
      "@version" => "1",
       "message" => "error 500 http",
          "path" => "/root/test.log",
          "host" => "localhost.localdomain",
    "@timestamp" => 2021-03-01T08:50:49.739Z
}
{
      "@version" => "1",
       "message" => "error 500 http",
          "path" => "/root/test.log",
          "host" => "localhost.localdomain",
    "@timestamp" => 2021-03-01T08:50:49.739Z
}
{
      "@version" => "1",
       "message" => "error 500 http",
          "path" => "/root/test.log",
          "host" => "localhost.localdomain",
    "@timestamp" => 2021-03-01T08:50:49.739Z
}

# 追加日志信息
$echo "hello jack" >> test.log
$bin/logstash -f etc/file.conf
{
       "message" => "hello jack",
      "@version" => "1",
          "path" => "/root/test.log",
          "host" => "localhost.localdomain",
    "@timestamp" => 2021-03-01T08:54:26.073Z
}

# 删除记录位置的文件
$rm -fr /usr/local/logstash-6.5.4/data/plugins/inputs/file/.sincedb_fb3a69a4e68cf54f41cca925731860e0
$bin/logstash -f etc/file.conf
{
       "message" => "error 500 http",
      "@version" => "1",
          "host" => "localhost.localdomain",
    "@timestamp" => 2021-03-01T08:58:45.533Z,
          "path" => "/root/test.log"
}
{
       "message" => "error 500 http",
      "@version" => "1",
          "host" => "localhost.localdomain",
    "@timestamp" => 2021-03-01T08:58:45.945Z,
          "path" => "/root/test.log"
}
{
       "message" => "error 500 http",
      "@version" => "1",
          "host" => "localhost.localdomain",
    "@timestamp" => 2021-03-01T08:58:45.946Z,
          "path" => "/root/test.log"
}
{
       "message" => "error 500 http",
      "@version" => "1",
          "host" => "localhost.localdomain",
    "@timestamp" => 2021-03-01T08:58:45.946Z,
          "path" => "/root/test.log"
}
{
       "message" => "error 500 http",
      "@version" => "1",
          "host" => "localhost.localdomain",
    "@timestamp" => 2021-03-01T08:58:45.946Z,
          "path" => "/root/test.log"
}
{
       "message" => "error 500 http",
      "@version" => "1",
          "host" => "localhost.localdomain",
    "@timestamp" => 2021-03-01T08:58:45.947Z,
          "path" => "/root/test.log"
}
{
       "message" => "error 500 http",
      "@version" => "1",
          "host" => "localhost.localdomain",
    "@timestamp" => 2021-03-01T08:58:45.947Z,
          "path" => "/root/test.log"
}
{
       "message" => "error 500 http",
      "@version" => "1",
          "host" => "localhost.localdomain",
    "@timestamp" => 2021-03-01T08:58:45.949Z,
          "path" => "/root/test.log"
}
{
       "message" => "error 500 http",
      "@version" => "1",
          "host" => "localhost.localdomain",
    "@timestamp" => 2021-03-01T08:58:45.949Z,
          "path" => "/root/test.log"
}
{
       "message" => "error 500 http",
      "@version" => "1",
          "host" => "localhost.localdomain",
    "@timestamp" => 2021-03-01T08:58:45.949Z,
          "path" => "/root/test.log"
}
{
       "message" => "hello jack",
      "@version" => "1",
          "host" => "localhost.localdomain",
    "@timestamp" => 2021-03-01T08:58:45.949Z,
          "path" => "/root/test.log"
}

# 读取json格式数据
$vi /etc/json.conf
input {
  stdin {
    codec => "json"
    # codec {
    #   json {"charset" => "UTF-8"}
    # }
  } 
}
output {
  stdout {}
}

$bin/logstash -f etc/json.conf
{"name": "coderZsq", "age": 18, "hobby": "java"}
{
          "name" => "coderZsq",
         "hobby" => "java",
          "host" => "localhost.localdomain",
           "age" => 18,
    "@timestamp" => 2021-03-01T09:12:42.607Z,
      "@version" => "1"
}
123456789
[2021-03-01T04:12:58,031][WARN ][logstash.codecs.jsonlines] JSON parse error, original data now in message field {:error=>#<LogStash::Json::ParserError: incompatible json object type=java.lang.Integer , only hash map or arrays are supported>, :data=>"123456789"}
{
    "@timestamp" => 2021-03-01T09:12:58.054Z,
          "tags" => [
        [0] "_jsonparsefailure"
    ],
       "message" => "123456789",
      "@version" => "1",
          "host" => "localhost.localdomain"
}

# 读取多行文本内容
$pwd
/root

$vi wms.log
Exception in thread "main" java.lang.ArithmeticException: / by zero
	at Main.main(Main.java:3)

Process finished with exit code 1

$pwd
/usr/local/logstash-6.5.4

$cp etc/file.conf etc/mutil.conf
$vi etc/mutil.conf
input {
  file {
    path => "/root/wms.log"
    start_position => "beginning"
    stat_interval => "2"
  }
}
output {
  stdout { codec => rubydebug }
}

$bin/logstash -f etc/mutil.conf
{
       "message" => "Exception in thread \"main\" java.lang.ArithmeticException: / by zero",
    "@timestamp" => 2021-03-01T09:27:56.271Z,
          "path" => "/root/wms.log",
          "host" => "localhost.localdomain",
      "@version" => "1"
}
{
       "message" => "\tat Main.main(Main.java:3)",
    "@timestamp" => 2021-03-01T09:27:56.365Z,
          "path" => "/root/wms.log",
          "host" => "localhost.localdomain",
      "@version" => "1"
}
{
       "message" => "",
    "@timestamp" => 2021-03-01T09:27:56.369Z,
          "path" => "/root/wms.log",
          "host" => "localhost.localdomain",
      "@version" => "1"
}
{
       "message" => "Process finished with exit code 1",
    "@timestamp" => 2021-03-01T09:27:56.369Z,
          "path" => "/root/wms.log",
          "host" => "localhost.localdomain",
      "@version" => "1"
}
{
       "message" => "",
    "@timestamp" => 2021-03-01T09:27:56.370Z,
          "path" => "/root/wms.log",
          "host" => "localhost.localdomain",
      "@version" => "1"
}

$vi etc/mutil.conf
input {
  file {
    path => "/root/wms.log"
    start_position => "beginning"
    stat_interval => "2"
    codec => multiline {
      pattern => "^\["
      negate => "true"
      what => "previous"
    }
  }
}
output {
  stdout { codec => rubydebug }
}

$bin/logstash -f etc/mutil.conf
{
       "message" => "Exception in thread \"main\" java.lang.ArithmeticException: / by zero\n\tat Main.main(Main.java:3)\nProcess finished with exit code 1",
          "host" => "localhost.localdomain",
      "@version" => "1",
    "@timestamp" => 2021-03-01T09:42:18.806Z,
          "path" => "/root/wms.log",
          "tags" => [
        [0] "multiline"
    ]
}

# 输出数据保存到ES中
$vi etc/es.conf
input {
  stdin {
    codec => "json"
  }
}
output {
  elasticsearch {
    index => "json"
    hosts => ["172.16.23.125:9200"]
  }
  stdout {}
}

$bin/logstash -f etc/es.conf
Sending Logstash logs to /usr/local/logstash-6.5.4/logs which is now configured via log4j2.properties
[2021-03-01T04:54:31,564][WARN ][logstash.config.source.multilocal] Ignoring the 'pipelines.yml' file because modules or command line options are specified
[2021-03-01T04:54:31,630][INFO ][logstash.runner          ] Starting Logstash {"logstash.version"=>"6.5.4"}
[2021-03-01T04:54:42,724][INFO ][logstash.pipeline        ] Starting pipeline {:pipeline_id=>"main", "pipeline.workers"=>1, "pipeline.batch.size"=>125, "pipeline.batch.delay"=>50}
[2021-03-01T04:54:44,456][INFO ][logstash.outputs.elasticsearch] Elasticsearch pool URLs updated {:changes=>{:removed=>[], :added=>[http://172.16.23.125:9200/]}}
[2021-03-01T04:54:45,976][WARN ][logstash.outputs.elasticsearch] Restored connection to ES instance {:url=>"http://172.16.23.125:9200/"}
[2021-03-01T04:54:46,566][INFO ][logstash.outputs.elasticsearch] ES Output version determined {:es_version=>6}
[2021-03-01T04:54:46,595][WARN ][logstash.outputs.elasticsearch] Detected a 6.x and above cluster: the `type` event field won't be used to determine the document _type {:es_version=>6}
[2021-03-01T04:54:46,673][INFO ][logstash.outputs.elasticsearch] New Elasticsearch output {:class=>"LogStash::Outputs::ElasticSearch", :hosts=>["//172.16.23.125:9200"]}
[2021-03-01T04:54:46,750][INFO ][logstash.outputs.elasticsearch] Using mapping template from {:path=>nil}
[2021-03-01T04:54:46,849][INFO ][logstash.outputs.elasticsearch] Attempting to install template {:manage_template=>{"template"=>"logstash-*", "version"=>60001, "settings"=>{"index.refresh_interval"=>"5s"}, "mappings"=>{"_default_"=>{"dynamic_templates"=>[{"message_field"=>{"path_match"=>"message", "match_mapping_type"=>"string", "mapping"=>{"type"=>"text", "norms"=>false}}}, {"string_fields"=>{"match"=>"*", "match_mapping_type"=>"string", "mapping"=>{"type"=>"text", "norms"=>false, "fields"=>{"keyword"=>{"type"=>"keyword", "ignore_above"=>256}}}}}], "properties"=>{"@timestamp"=>{"type"=>"date"}, "@version"=>{"type"=>"keyword"}, "geoip"=>{"dynamic"=>true, "properties"=>{"ip"=>{"type"=>"ip"}, "location"=>{"type"=>"geo_point"}, "latitude"=>{"type"=>"half_float"}, "longitude"=>{"type"=>"half_float"}}}}}}}}
[2021-03-01T04:54:47,205][INFO ][logstash.inputs.stdin    ] Automatically switching from json to json_lines codec {:plugin=>"stdin"}
[2021-03-01T04:54:47,870][INFO ][logstash.pipeline        ] Pipeline started successfully {:pipeline_id=>"main", :thread=>"#<Thread:0x5d9f5d9b run>"}
The stdin plugin is now waiting for input:
[2021-03-01T04:54:48,207][INFO ][logstash.outputs.elasticsearch] Installing elasticsearch template to _template/logstash
[2021-03-01T04:54:48,262][INFO ][logstash.agent           ] Pipelines running {:count=>1, :running_pipelines=>[:main], :non_running_pipelines=>[]}
[2021-03-01T04:54:51,497][INFO ][logstash.agent           ] Successfully started Logstash API endpoint {:port=>9600}
{"name": "coderZsq", "age": 18, "hobby": "java"}
{
         "hobby" => "java",
          "name" => "coderZsq",
          "host" => "localhost.localdomain",
      "@version" => "1",
    "@timestamp" => 2021-03-01T09:55:35.758Z,
           "age" => 18
}