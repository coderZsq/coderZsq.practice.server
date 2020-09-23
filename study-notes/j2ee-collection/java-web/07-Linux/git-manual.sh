# 安装完成后, 还需要最后一步设置, 在命令行输入:
# 因为Git是分布式版本控制系统, 所以, 每个机器都必须自报家门: 你的名字和Email地址
# 注意git config命令的 --global参数, 用了这个参数, 表示你这台机器上所有的Git仓库都会使用这个配置, 当然也可以对某个仓库指定不同的用户名和Email地址
$ git config --global user.name "Your Name"
$ git config --global user.email "email@example.com"
# 存储在 $ cat /Users/zhushuangquan/.gitconfig 文件中
# [user]
# 	name = coderZsq
# 	email = a13701777868@yahoo.com
# [core]
# 	excludesfile = /Users/zhushuangquan/.gitignore_global
# [difftool "sourcetree"]
# 	cmd = opendiff \"$LOCAL\" \"$REMOTE\"
# 	path =
# [mergetool "sourcetree"]
# 	cmd = /Applications/Sourcetree.app/Contents/Resources/opendiff-w.sh \"$LOCAL\" \"$REMOTE\" -ancestor \"$BASE\" -merge \"$MERGED\"
# 	trustExitCode = true
# [commit]
# 	template = /Users/zhushuangquan/.stCommitMsg
# [filter "lfs"]
# 	clean = git-lfs clean -- %f
# 	smudge = git-lfs smudge -- %f
# 	process = git-lfs filter-process
# 	required = true

# 初始化仓库
$ git init
# Initialized empty Git repository in /Users/zhushuangquan/Desktop/project/.git/

# 把文件添加到版本库
$ touch a.txt # 111
$ touch b.txt # 222
$ git add a.txt
$ git add b.txt

# 把代码添加到版本管理
$ git commit -m 'init data'
# [master (root-commit) 6f10aae] init data
#  2 files changed, 2 insertions(+)
#  create mode 100644 a.txt
#  create mode 100644 b.txt

# 查看存储的数据类型 -t
# $ cd /project/.git/objects
# $ tree
# .
# ├── 1c
# │   └── 1493dabe676ab5834e9edae0747a7a3d5ac5d2
# ├── 6d
# │   └── d90d24d319b452859920bf74120405fcdaa017
# ├── 6f
# │   └── 10aaed8080d6a671ad7bdf8a19450e11b85220
# ├── 9d
# │   └── 07aa0df55c353e18eea6f1b401946b5dad7bce
# ├── info
# └── pack
# 1c为文件夹名, 1493为文件哈希
$ git cat-file -t 1c1493
# tree

$ git cat-file -t 6dd90d
# blob

$ git cat-file -t 6f10aa
# commit

# 查看内容 -p 
$ git cat-file -p 6dd90d # blob
# 222
$ git cat-file -p 9d07aa # blob
# 111
$ git cat-file -p 6f10aa # commit
# tree 1c1493dabe676ab5834e9edae0747a7a3d5ac5d2
# author coderZsq <a13701777868@yahoo.com> 1600830895 +0800
# committer coderZsq <a13701777868@yahoo.com> 1600830895 +0800

# init data
$ git cat-file -p 1c1493 # tree
# 100644 blob 9d07aa0df55c353e18eea6f1b401946b5dad7bce	a.txt
# 100644 blob 6dd90d24d319b452859920bf74120405fcdaa017	b.txt

# 修改文件
$ vi a.txt # 333
# 1. 把代码放到本地仓库
# 2. 把index缓存区里面的索引关联最新的文件
$ git add a.txt
# $ tree
# .
# ├── 1c
# │   └── 1493dabe676ab5834e9edae0747a7a3d5ac5d2
# ├── 4f
# │   └── 37670799715b48e31ab1be1c419f6ddbb19056
# ├── 6d
# │   └── d90d24d319b452859920bf74120405fcdaa017
# ├── 6f
# │   └── 10aaed8080d6a671ad7bdf8a19450e11b85220
# ├── 9d
# │   └── 07aa0df55c353e18eea6f1b401946b5dad7bce
# ├── info
# └── pack
$ git cat-file -p 4f3767
# 333
$ git commit -m 'update a.txt'
# 1. 生成一个tree类型的object对象
# 2. 生成一个commit类型的object对象4dafb2
# 3. 生成的commit类型的object对象会以链表的形式指向前面节点
# 4. 当前的指针head->master->commit对象
# [master 4dafb2b] update a.txt
#  1 file changed, 1 insertion(+), 1 deletion(-)

# 查看日志
$ git reflog
# 4dafb2b (HEAD -> master) HEAD@{0}: commit: update a.txt
# 6f10aae HEAD@{1}: commit (initial): init data
$ git cat-file -p 4dafb2
# tree 94f5ccd861249f5bafce287c014db64bf4d21a6a
# parent 6f10aaed8080d6a671ad7bdf8a19450e11b85220
# author coderZsq <a13701777868@yahoo.com> 1600833000 +0800
# committer coderZsq <a13701777868@yahoo.com> 1600833000 +0800

# update a.txt
$ git cat-file -p 94f5cc
# 100644 blob 4f37670799715b48e31ab1be1c419f6ddbb19056	a.txt
# 100644 blob 6dd90d24d319b452859920bf74120405fcdaa017	b.txt
$ git cat-file -p 4f3767
# 333

# 查看版本库的状态
$ git status
# On branch master
# nothing to commit, working tree clean
$ touch c.txt # 666
$ git status
# On branch master
# Untracked files:
#   (use "git add <file>..." to include in what will be committed)
# 	c.txt

# nothing added to commit but untracked files present (use "git add" to track)
$ git add c.txt
$ git status
# On branch master
# Changes to be committed:
#   (use "git restore --staged <file>..." to unstage)
# 	new file:   c.txt
$ git commit -m 'add c.txt'
# [master b6322dd] add c.txt
#  1 file changed, 1 insertion(+)
#  create mode 100644 c.txt

# 查看差异
$ touch d.txt # 888
$ git add d.txt
$ git diff --cached
# diff --git a/d.txt b/d.txt
# new file mode 100644
# index 0000000..cd6be37
# --- /dev/null
# +++ b/d.txt
# @@ -0,0 +1 @@
# +888
# \ No newline at end of file
$ git commit -m 'add d.txt'
$ vi d.txt # 888\n999
$ git diff
# diff --git a/d.txt b/d.txt
# index cd6be37..3088b6f 100644
# --- a/d.txt
# +++ b/d.txt
# @@ -1 +1,2 @@
# -888
# \ No newline at end of file
# +888
# +999
# \ No newline at end of file

# 版本回退
$ git reflog
# 63ebc9e (HEAD -> master) HEAD@{0}: commit: add d.txt
# b6322dd HEAD@{1}: commit: add c.txt
# 4dafb2b HEAD@{2}: commit: update a.txt
# 6f10aae HEAD@{3}: commit (initial): init data
$ git reset --hard 6f10aae
# HEAD is now at 6f10aae init data 
$ git reflog
# 6f10aae (HEAD -> master) HEAD@{0}: reset: moving to 6f10aae
# 63ebc9e HEAD@{1}: commit: add d.txt
# b6322dd HEAD@{2}: commit: add c.txt
# 4dafb2b HEAD@{3}: commit: update a.txt
# 6f10aae (HEAD -> master) HEAD@{4}: commit (initial): init data
$ git reset --hard 63ebc9e
# HEAD is now at 63ebc9e add d.txt

# 管理修改
$ vi d.txt # 888\n999
$ git add d.txt
$ vi d.txt # 888\n999\n777
$ git commit -m 'test'
# [master 653162f] test
#  1 file changed, 2 insertions(+), 1 deletion(-)
$ git cat-file -p 3088b6f
# 888
# 999
$ git add .
$ git commit -m 'test2'
# [master 54315db] test2
#  1 file changed, 2 insertions(+), 1 deletion(-)

# 撤销修改
$ vi d.txt # 888\n999\n777\n111
$ cat d.txt
# 888
# 999
# 777
# 111
$ git checkout -- d.txt
$ cat d.txt
# 888
# 999
# 777
$ vi d.txt # 888\n999\n777\n111
$ git add d.txt
$ vi d.txt # 888\n999\n777\n111\n8989889
$ git checkout -- d.txt
$ cat d.txt
# 888
# 999
# 777
# 111

# 删除文件
$ rm c.txt
$ rm d.txt
$ git status
# On branch master
# Changes to be committed:
#   (use "git restore --staged <file>..." to unstage)
# 	modified:   d.txt

# Changes not staged for commit:
#   (use "git add/rm <file>..." to update what will be committed)
#   (use "git restore <file>..." to discard changes in working directory)
# 	deleted:    c.txt
# 	deleted:    d.txt
$ git add .
$ git status
# On branch master
# Changes to be committed:
#   (use "git restore --staged <file>..." to unstage)
# 	deleted:    c.txt
# 	deleted:    d.txt
$ git commit -m 'delete file'
# [master c6b2df7] delete file
#  2 files changed, 4 deletions(-)
#  delete mode 100644 c.txt
#  delete mode 100644 d.txt

# 历史日志
$ git reflog
# c6b2df7 (HEAD -> master) HEAD@{0}: commit: delete file
# 54315db HEAD@{1}: commit: test2
# 653162f HEAD@{2}: commit: test
# 63ebc9e HEAD@{3}: reset: moving to 63ebc9e
# 6f10aae HEAD@{4}: reset: moving to 6f10aae
# 63ebc9e HEAD@{5}: commit: add d.txt
# b6322dd HEAD@{6}: commit: add c.txt
# 4dafb2b HEAD@{7}: commit: update a.txt
# 6f10aae HEAD@{8}: commit (initial): init data

# 修改文件名
# 修改名字不会影响原来的数据Blob对象的修改
$ mv b.txt bbbb.txt
$ git status
# On branch master
# Changes not staged for commit:
#   (use "git add/rm <file>..." to update what will be committed)
#   (use "git restore <file>..." to discard changes in working directory)
# 	deleted:    b.txt

# Untracked files:
#   (use "git add <file>..." to include in what will be committed)
# 	bbbb.txt

# no changes added to commit (use "git add" and/or "git commit -a")
$ git add .
$ git status
# On branch master
# Changes to be committed:
#   (use "git restore --staged <file>..." to unstage)
# 	renamed:    b.txt -> bbbb.txt
$ git commit -m 'test'
# [master 632ba38] test
#  1 file changed, 0 insertions(+), 0 deletions(-)
#  rename b.txt => bbbb.txt (100%)
$ git cat-file -p 632ba38
# tree 514fa836a1b1aded226aacfa9116aace97ab4103
# parent c6b2df73103747fe7ecac0694b5697180c0a2878
# author coderZsq <a13701777868@yahoo.com> 1600835678 +0800
# committer coderZsq <a13701777868@yahoo.com> 1600835678 +0800

# test
$ git cat-file -p 514fa8
# 100644 blob 4f37670799715b48e31ab1be1c419f6ddbb19056	a.txt
# 100644 blob 6dd90d24d319b452859920bf74120405fcdaa017	bbbb.txt
$ git cat-file -p 6dd90d
# 222

$ mv bbbb.txt testaaa.txt
$ git add .
$ git commit -m 'test3'
# [master 966c568] test3
#  1 file changed, 0 insertions(+), 0 deletions(-)
#  rename bbbb.txt => testaaa.txt (100%)
$ git cat-file -p 966c568
# tree e43bbce6cb53d5bb1dc0c7ef7796b666f605e6c6
# parent 632ba384e4de535815b56567260707c69238fe49
# author coderZsq <a13701777868@yahoo.com> 1600836021 +0800
# committer coderZsq <a13701777868@yahoo.com> 1600836021 +0800

# test3
$ git cat-file -p e43bbc
# 100644 blob 4f37670799715b48e31ab1be1c419f6ddbb19056	a.txt
# 100644 blob 6dd90d24d319b452859920bf74120405fcdaa017	testaaa.txt

# 文件内容追溯
$ git blame a.txt
# 4dafb2b0 (coderZsq 2020-09-23 11:50:00 +0800 1) 333

# 忽略文件
$ mkdir target
$ touch idea.imi
$ touch .gitignore
$ vi .gitignore
# target/
# idea.imi
$ git add .
$ git status
# On branch master
# Changes to be committed:
#   (use "git restore --staged <file>..." to unstage)
# 	new file:   .gitignore
$ git commit -m 'git ignore'
# [master 9a7986a] git ignore
#  1 file changed, 2 insertions(+)
#  create mode 100644 .gitignore
$ cp a.txt /target/a.txt
$ cp testaaa.txt /target/testaaa.txt
$ git add .
$ git status
# On branch master
# nothing to commit, working tree clean

# 储藏
$ vi a.txt # 333\nhello java
# 保存
$ git stash save fixbug009
# Saved working directory and index state On master: fixbug009
$ git status
# On branch master
# nothing to commit, working tree clean
# 查看
$ git stash list
# stash@{0}: On master: fixbug009
$ git reflog
# 9a7986a (HEAD -> master) HEAD@{0}: reset: moving to HEAD
# 9a7986a (HEAD -> master) HEAD@{1}: commit: git ignore
# 966c568 HEAD@{2}: commit: test3
# 632ba38 HEAD@{3}: commit: test
# c6b2df7 HEAD@{4}: commit: delete file
# 54315db HEAD@{5}: commit: test2
# 653162f HEAD@{6}: commit: test
# 63ebc9e HEAD@{7}: reset: moving to 63ebc9e
# 6f10aae HEAD@{8}: reset: moving to 6f10aae
# 63ebc9e HEAD@{9}: commit: add d.txt
# b6322dd HEAD@{10}: commit: add c.txt
# 4dafb2b HEAD@{11}: commit: update a.txt
# 6f10aae HEAD@{12}: commit (initial): init data
$ vi testaaa.txt #222\njava learn
$ git add .
$ git commit -m 'fixbug2009'
# [master 54e8ef5] fixbug2009
#  1 file changed, 2 insertions(+), 1 deletion(-)
$ git status
# On branch master
# nothing to commit, working tree clean
# 恢复
$ git stash pop
# On branch master
# Changes not staged for commit:
#   (use "git add <file>..." to update what will be committed)
#   (use "git restore <file>..." to discard changes in working directory)
# 	modified:   a.txt

# no changes added to commit (use "git add" and/or "git commit -a")
# Dropped refs/stash@{0} (1f4c5bf915f36c93710f97e41e50fedfb77ebf16)

# 清除
$ git stash clear