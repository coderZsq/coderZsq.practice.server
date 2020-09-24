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

# 查看暂存区数据
$ git ls-files -s
# 100644 a219aab546dba819fd10e2820fa3561ba2f47783 0	.gitignore
# 100644 4f37670799715b48e31ab1be1c419f6ddbb19056 0	a.txt
# 100644 e14dca8c1a652939cdde1e7b4da66563687fb1b7 0	testaaa.txt
$ touch c.txt # ccc
$ git add .
$ git ls-files -s
# 100644 a219aab546dba819fd10e2820fa3561ba2f47783 0	.gitignore
# 100644 5330b1520aa1c73bf75a9eb467ac4462dd9fdc55 0	a.txt
# 100644 2383bd587474492050db93523ca1bb4faf7773a0 0	c.txt
# 100644 e14dca8c1a652939cdde1e7b4da66563687fb1b7 0	testaaa.txt
$ git commit -m 'add c.txt'
# [master 2e2047c] add c.txt
#  2 files changed, 3 insertions(+), 1 deletion(-)
#  create mode 100644 c.txt
$ git ls-files -s
# 100644 a219aab546dba819fd10e2820fa3561ba2f47783 0	.gitignore
# 100644 5330b1520aa1c73bf75a9eb467ac4462dd9fdc55 0	a.txt
# 100644 2383bd587474492050db93523ca1bb4faf7773a0 0	c.txt
# 100644 e14dca8c1a652939cdde1e7b4da66563687fb1b7 0	testaaa.txt
$ vi a.txt # 333
$ git status
# On branch master
# Changes not staged for commit:
#   (use "git add <file>..." to update what will be committed)
#   (use "git restore <file>..." to discard changes in working directory)
# 	modified:   a.txt

# no changes added to commit (use "git add" and/or "git commit -a")
$ git add a.txt
$ git ls-files -s
# 100644 a219aab546dba819fd10e2820fa3561ba2f47783 0	.gitignore
# 100644 4f37670799715b48e31ab1be1c419f6ddbb19056 0	a.txt
# 100644 2383bd587474492050db93523ca1bb4faf7773a0 0	c.txt
# 100644 e14dca8c1a652939cdde1e7b4da66563687fb1b7 0	testaaa.txt
$ git cat-file -p 4f37
# 333
$ git reflog
# 2e2047c (HEAD -> master) HEAD@{0}: commit: add c.txt
# 54e8ef5 HEAD@{1}: commit: fixbug2009
# 9a7986a HEAD@{2}: reset: moving to HEAD
# 9a7986a HEAD@{3}: commit: git ignore
# 966c568 HEAD@{4}: commit: test3
# 632ba38 HEAD@{5}: commit: test
# c6b2df7 HEAD@{6}: commit: delete file
# 54315db HEAD@{7}: commit: test2
# 653162f HEAD@{8}: commit: test
# 63ebc9e HEAD@{9}: reset: moving to 63ebc9e
# 6f10aae HEAD@{10}: reset: moving to 6f10aae
# 63ebc9e HEAD@{11}: commit: add d.txt
# b6322dd HEAD@{12}: commit: add c.txt
# 4dafb2b HEAD@{13}: commit: update a.txt
# 6f10aae HEAD@{14}: commit (initial): init data
$ git commit -m 'update'
# [master ae3e8fd] update
#  1 file changed, 1 insertion(+), 2 deletions(-)
$ git cat-file -p ae3e8fd
# tree 753ba4916c24794b5517ca4958632bbf234df232
# parent 2e2047cff4b43db3bf1545fe7c094cbb64531b9b
# author coderZsq <a13701777868@yahoo.com> 1600917109 +0800
# committer coderZsq <a13701777868@yahoo.com> 1600917109 +0800

# update

# HEAD关联关系
$ cat HEAD
# ref: refs/heads/master
$ cat master
# ae3e8fd63ed4ecca84bfeca8656a8ce0f874955d

# 手动创建分支并指向
$ cd .git/refs/heads
# 1. 创建分支dev
$ touch dev
# 2. 并且让dev分支指向指定commit
$ vi dev # 2e2047cff4b43db3bf1545fe7c094cbb64531b9b
# 3. 修改当前head指向dev分支
$ vi HEAD # ref: refs/heads/dev
$ git status
# On branch dev
# Changes to be committed:
#   (use "git restore --staged <file>..." to unstage)
# 	modified:   a.txt
$ git ls-files -s
# 100644 a219aab546dba819fd10e2820fa3561ba2f47783 0	.gitignore
# 100644 4f37670799715b48e31ab1be1c419f6ddbb19056 0	a.txt
# 100644 2383bd587474492050db93523ca1bb4faf7773a0 0	c.txt
# 100644 e14dca8c1a652939cdde1e7b4da66563687fb1b7 0	testaaa.txt
$ git cat-file -p 4f37
# 333
$ git reflog
# ae3e8fd (master) HEAD@{0}: commit: update
# 2e2047c (HEAD -> dev) HEAD@{1}: commit: add c.txt
# 54e8ef5 HEAD@{2}: commit: fixbug2009
# 9a7986a HEAD@{3}: reset: moving to HEAD
# 9a7986a HEAD@{4}: commit: git ignore
# 966c568 HEAD@{5}: commit: test3
# 632ba38 HEAD@{6}: commit: test
# c6b2df7 HEAD@{7}: commit: delete file
# 54315db HEAD@{8}: commit: test2
# 653162f HEAD@{9}: commit: test
# 63ebc9e HEAD@{10}: reset: moving to 63ebc9e
# 6f10aae HEAD@{11}: reset: moving to 6f10aae
# 63ebc9e HEAD@{12}: commit: add d.txt
# b6322dd HEAD@{13}: commit: add c.txt
# 4dafb2b HEAD@{14}: commit: update a.txt
# 6f10aae HEAD@{15}: commit (initial): init data
$ git checkout dev
# M	a.txt
# Already on 'dev'
$ git cat-file -p 2e2047c
# tree 6283f154a6fb656ada570b5283aa01690eb321c7
# parent 54e8ef55bae86fc52920fd3d4a35df68a009d01a
# author coderZsq <a13701777868@yahoo.com> 1600916663 +0800
# committer coderZsq <a13701777868@yahoo.com> 1600916663 +0800

# add c.txt
$ git cat-file -p 6283f1
# 100644 blob a219aab546dba819fd10e2820fa3561ba2f47783	.gitignore
# 100644 blob 5330b1520aa1c73bf75a9eb467ac4462dd9fdc55	a.txt
# 100644 blob 2383bd587474492050db93523ca1bb4faf7773a0	c.txt
# 100644 blob e14dca8c1a652939cdde1e7b4da66563687fb1b7	testaaa.txt
$ git cat-file -p 5330b
# 333
# hello java
$ git add .
$ git commit -m 'test'
# [dev e39a5f4] test
#  1 file changed, 1 insertion(+), 2 deletions(-)

# 分支管理
# 查看分支
$ git branch
# * dev
#   master
# 切换分支
$ git checkout master
# Switched to branch 'master'
# 创建分支
$ git branch test
# $ git branch -a
#   dev
# * master
#   test
# 创建并切换分支
$ git checkout -b fixbug2009
# Switched to a new branch 'fixbug2009'
$ git branch -a
#   dev
# * fixbug2009
#   master
#   test
# 删除分支
$ git branch -D test
# Deleted branch test (was ae3e8fd).
$ git branch -D dev
# Deleted branch dev (was e39a5f4).
$ git branch -D fixbug2009
# error: Cannot delete branch 'fixbug2009' checked out at '/Users/zhushuangquan/Desktop/project'
$ git checkout master
# Switched to branch 'master'
$ git branch -D fixbug2009
# Deleted branch fixbug2009 (was ae3e8fd).
$ git branch -a
# * master

# 合并分支
$ git branch dev
$ git branch test
$ git checkout dev
# Switched to branch 'dev'
$ touch dev.txt
$ git add .
$ git commit -m 'dev:add dev.txt'
# [dev 9682ecc] dev:add dev.txt
#  1 file changed, 0 insertions(+), 0 deletions(-)
#  create mode 100644 dev.txt
$ git checkout test
# Switched to branch 'test'
$ touch test.txt
$ git add .
$ git commit -m 'test.txt'
# [test 81edccb] test.txt
#  1 file changed, 0 insertions(+), 0 deletions(-)
#  create mode 100644 test.txt
$ git status
# On branch test
# nothing to commit, working tree clean
$ git merge dev -m 'merge dev branch'
# Merge made by the 'recursive' strategy.
#  dev.txt | 0
#  1 file changed, 0 insertions(+), 0 deletions(-)
#  create mode 100644 dev.txt

# 文件冲突合并
$ git checkout master
# Switched to branch 'master'
$ git branch -D dev
# Deleted branch dev (was 9682ecc).
$ git branch -D test
# Deleted branch test (was eecc09c).
$ git branch dev
$ git branch test
$ git checkout dev
# Switched to branch 'dev'
$ vi a.txt # a1
$ git add .
$ git commit -m 'dev'
# [dev 0d66ca0] dev
#  1 file changed, 1 insertion(+), 1 deletion(-)
$ git checkout test
# Switched to branch 'test'
$ vi a.txt # a2
$ git add .
$ git commit -m 'test'
# [test 91107c3] test
#  1 file changed, 1 insertion(+), 1 deletion(-)
$ git status
# On branch test
# nothing to commit, working tree clean
$ git merge dev -m 'merge dev'
# Auto-merging a.txt
# CONFLICT (content): Merge conflict in a.txt
# Automatic merge failed; fix conflicts and then commit the result.
$ cat a.txt
# <<<<<<< HEAD
# a2
# =======
# a1
# >>>>>>> dev
$ git status
# On branch test
# You have unmerged paths.
#   (fix conflicts and run "git commit")
#   (use "git merge --abort" to abort the merge)

# Unmerged paths:
#   (use "git add <file>..." to mark resolution)
# 	both modified:   a.txt

# no changes added to commit (use "git add" and/or "git commit -a")
# 终止合并
$ git merge --abort
# 解决冲突
$  git merge dev -m 'merge dev'
# Auto-merging a.txt
# CONFLICT (content): Merge conflict in a.txt
# Automatic merge failed; fix conflicts and then commit the result.
$ vi a.txt
# a2
# a1
$ git add .
$ git status
# On branch test
# All conflicts fixed but you are still merging.
#   (use "git commit" to conclude merge)

# Changes to be committed:
# 	modified:   a.txt
$ git commit -m 'fix both update'
# [test b9dedf8] fix both update

# 远程仓库
$ git remote add origin https://github.com/coderZsq/project.git
$ git push -u origin master
# Enumerating objects: 36, done.
# Counting objects: 100% (36/36), done.
# Delta compression using up to 8 threads
# Compressing objects: 100% (25/25), done.
# Writing objects: 100% (36/36), 2.78 KiB | 949.00 KiB/s, done.
# Total 36 (delta 7), reused 0 (delta 0)
# remote: Resolving deltas: 100% (7/7), done.
# To https://github.com/coderZsq/project.git
#  * [new branch]      master -> master
# Branch 'master' set up to track remote branch 'master' from 'origin'.
$ mkdir repo
$ cd repo
# 克隆仓库
$ git clone https://github.com/coderZsq/project.git
# Cloning into 'project'...
# remote: Enumerating objects: 36, done.
# remote: Counting objects: 100% (36/36), done.
# remote: Compressing objects: 100% (18/18), done.
# remote: Total 36 (delta 7), reused 36 (delta 7), pack-reused 0
# Unpacking objects: 100% (36/36), done.
$ cd project
$ git status
# On branch master
# Your branch is up to date with 'origin/master'.

# nothing to commit, working tree clean

# 推送
$ touch b.txt
$ git status
# On branch test
# Untracked files:
#   (use "git add <file>..." to include in what will be committed)
# 	b.txt

# nothing added to commit but untracked files present (use "git add" to track)
$ git add .
$ git commit -m 'update'
# [test 377b2df] update
#  1 file changed, 0 insertions(+), 0 deletions(-)
#  create mode 100644 b.txt
$ git status
# On branch master
# Your branch is ahead of 'origin/master' by 1 commit.
#   (use "git push" to publish your local commits)

# nothing to commit, working tree clean
$ git push
# Enumerating objects: 4, done.
# Counting objects: 100% (4/4), done.
# Delta compression using up to 8 threads
# Compressing objects: 100% (2/2), done.
# Writing objects: 100% (3/3), 259 bytes | 259.00 KiB/s, done.
# Total 3 (delta 1), reused 0 (delta 0)
# remote: Resolving deltas: 100% (1/1), completed with 1 local object.
# To https://github.com/coderZsq/project.git
#    ae3e8fd..086aad2  master -> master

# 拉取
$ git pull
# remote: Enumerating objects: 4, done.
# remote: Counting objects: 100% (4/4), done.
# remote: Compressing objects: 100% (1/1), done.
# remote: Total 3 (delta 1), reused 3 (delta 1), pack-reused 0
# Unpacking objects: 100% (3/3), done.
# From https://github.com/coderZsq/project
#    ae3e8fd..086aad2  master     -> origin/master
# Updating ae3e8fd..086aad2
# Fast-forward
#  b.txt | 0
#  1 file changed, 0 insertions(+), 0 deletions(-)
#  create mode 100644 b.txt

# 注释规范
# Commit Message
# Commit message could help reviewers better understand what is the purpose of submitted PR. It could help accelerate the code review procedure as well. We encourage contributors to use EXPLICIT commit message rather than ambiguous message. In general, we advocate the following commit message type:

# docs: xxxx. For example, "docs: add docs about Seata cluster installation".
# feature: xxxx.For example, "feature: support oracle in AT mode".
# bugfix: xxxx. For example, "bugfix: fix panic when input nil parameter".
# refactor: xxxx. For example, "refactor: simplify to make codes more readable".
# test: xxx. For example, "test: add unit test case for func InsertIntoArray".
# other readable and explicit expression ways.