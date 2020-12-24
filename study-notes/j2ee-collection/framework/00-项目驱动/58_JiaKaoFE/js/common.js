class Commons {
    static baseUrl

    static url(uri) {
        if (!uri) return
        if (uri.startsWith('http://')
            || uri.startsWith('https://')) return uri
        return this.baseUrl + uri
    }

    static isString(obj) {
        return (typeof obj === 'string') && obj.constructor === String;
    }

    static deepCopy(obj) {
        const newObj = (obj.constructor === Array) ? [] : {}
        return $.extend(true, newObj, obj)
    }
}

/********************* 请求 ***********************/
class Ajaxs {
    static loadGet(cfg) {
        cfg.type = 'GET'
        this.loadAjax(cfg)
    }

    static loadPost(cfg) {
        cfg.type = 'POST'
        this.loadAjax(cfg)
    }

    static loadAjax(cfg) {
        // 弹框
        const loadIdx = Layers.load()

        // 请求
        this.ajax({
            type: cfg.type,
            uri: cfg.uri,
            data: cfg.data,
            processData: cfg.processData,
            contentType: cfg.contentType,
            success: (data) => {
                // 完全自定义success
                if ($.isFunction(cfg.success)) {
                    cfg.success(data)
                    return
                }
                // 如果传的是字符串
                if (Commons.isString(cfg.success)) {
                    Layers.msgSuccess(data.msg || cfg.success)
                    return
                }
                // 如果传的是其他对象
                const subtitle = cfg.success && cfg.success.title
                const title = data.msg || subtitle || '操作成功'
                Layers.msgSuccess(title, () => {
                    cfg.success && cfg.success.after && cfg.success.after(data)
                })
            },
            error: (req) => {
                if ($.isFunction(cfg.error)) {
                    cfg.error(req)
                    return
                }
                const title = req.responseJSON && req.responseJSON.msg
                // 如果传的是字符串
                if (Commons.isString(cfg.error)) {
                    Layers.alertError(title || cfg.error)
                    return
                }
                // 如果传的是其他对象
                const subtitle = cfg.error && cfg.error.title
                const idx = Layers.alertError(title || subtitle || '操作失败', () => {
                    Layers.close(idx)
                    cfg.error && cfg.error.after && cfg.error.after(req)
                })
            },
            complete: (req) => {
                Layers.close(loadIdx)
                cfg.complete && cfg.complete(req)
            }
        })
    }

    static get(cfg) {
        cfg.type = 'GET'
        this.ajax(cfg)
    }

    static post(cfg) {
        cfg.type = 'POST'
        this.ajax(cfg)
    }

    static ajax(cfg) {
        cfg.url = Commons.url(cfg.uri)
        $.ajax(cfg)
    }
}

/********************* 弹框 ***********************/
class Layers {
    static _MSG_TIME = 1000
    static _SHADE = [0.5, '#fff']

    static index() {
        return layui.layer.index
    }

    static close(idx) {
        layui.layer.close(idx)
    }

    // load
    static load() {
        return layui.layer.load(1, {
            shade: this._SHADE
        })
    }

    // msg
    static msgSuccess(title, fn) {
        return this._msg(1, title, fn)
    }

    static msgError(title, fn) {
        return this._msg(2, title, fn)
    }

    static _msg(icon, title, fn) {
        return layui.layer.msg(title, {
            time: this._MSG_TIME,
            icon: icon,
            shade: this._SHADE
        }, fn)
    }

    // confirm
    static confirm(title, ok, cancel) {
        return layui.layer.confirm(title, {
            icon: 0,
            btn: ['确定', '取消']
        }, ok, cancel)
    }

    // open
    static openUri(uri, cfg) {
        const content = layui.miniPage.getHrefContent(uri)
        return this.open(content, cfg)
    }

    static open(content, cfg) {
        const openWH = layui.miniPage.getOpenWidthHeight()
        const openCfg = {
            type: 1,
            shade: 0.2,
            shadeClose: true,
            area: [openWH[0] + 'px', $(window).height() + 'px'],
            offset: ['0px', openWH[3] + 'px'],
            content: content,
            resize: false,
            move: false
        }
        $.extend(openCfg, cfg)
        return layui.layer.open(openCfg)
    }

    // alert
    static alertSuccess(title, fn) {
        return this._alert(1, title, fn)
    }

    static alertError(title, fn) {
        return this._alert(2, title, fn)
    }

    static _alert(icon, title, fn) {
        return layui.layer.alert(title, {
            icon: icon,
            shade: this._SHADE
        }, fn)
    }
}

/********************* 模块 ***********************/
class Module {
    constructor(cfg) {
        this._cfg = cfg
    }

    get(name) {
        return this[name] || this._cfg[name] || this['_' + name]
    }
}

class Select extends Module {
    // name/form
    constructor(cfg) {
        super(cfg)

        const sel = 'select[name=' + cfg.name + ']'
        this._$select = cfg.form.find(sel)
        this._filter = this._$select.attr('lay-filter')
        if (this._filter && cfg.change) { // 监听
            this._cfg.form.select(this._filter, () => {
                this.change()
            })
        }
    }

    empty() {
        this._$select.empty()
    }

    data(data, build) {
        this._data = data
        this._$options = []
        // 清空
        this.empty()
        // 根据数据构建option
        for (let i = 0; i < data.length; i++) {
            const obj = {}
            obj.$option = $('<option>')
            obj.index = i
            obj.data = data[i]
            const value = build(obj)
            if (value) {
                obj.$option.val(value.val)
                obj.$option.text(value.text)
            }
            this._$select.append(obj.$option)
            this._$options.push(obj.$option)
        }
        // 触发改变
        this.change()
        // 渲染
        this.render()
    }

    val(v) {
        this._$select.val(v)
        // 渲染
        this.render()
        // 触发事件
        this.change()
    }

    change() {
        if (!this._cfg.change) return
        const idx = this.selectedIndex()
        this._cfg.change({
            index: idx,
            data: this._data[idx],
            $option: this._$options[idx]
        })
    }

    selectedIndex() {
        return this._$select.prop('selectedIndex')
    }

    selectedData() {
        return this._data[this.selectedIndex()]
    }

    render() {
        layui.form.render('select')
    }
}

class Transfer extends Module {
    // id/selector/title/parseData
    constructor(cfg) {
        super(cfg)

        layui.transfer.render({
            id: cfg.id,
            elem: cfg.selector,
            title: cfg.title,
            height: 300
        })
    }

    data(data, build) {
        this._data = data

        layui.transfer.reload(this._cfg.id, {
            data,
            parseData: (item) => {
                const value = build(item)
                return {
                    title: value.text,
                    value: value.val
                }
            }
        })
    }

    val(value) {
        layui.transfer.reload(this._cfg.id, {
            data: this._data,
            value: value
        })
    }

    selectedData() {
        return layui.transfer.getData(this._cfg.id)
    }

    selectedValue() {
        return this.selectedData().map((r) => r.value)
    }
}

class Form extends Module {
    // selector
    constructor(cfg) {
        super(cfg)
        this._$form = $(cfg.selector)
    }

    $dom() {
        return this._$form
    }

    dom() {
        return this._$form[0]
    }

    reset() {
        this._$form[0].reset()
        this.find("[type=hidden]").val(null)
    }

    submit(filter, fn) {
        layui.form.on('submit(' + filter + ')', fn)
    }

    select(filter, fn) {
        layui.form.on('select(' + filter + ')', fn)
    }

    checkbox(filter, fn) {
        layui.form.on('checkbox(' + filter + ')', fn)
    }

    radio(filter, fn) {
        layui.form.on('radio(' + filter + ')', fn)
    }

    val(data) {
        layui.form.val(this._cfg.filter, data)
    }

    find(sel) {
        return this._$form.find(sel)
    }
}

class Table extends Module {
    constructor(cfg) {
        super(cfg)
        this._init()
    }

    _layuiTable() {
        return layui.table
    }

    toolbar(fn) {
        this._layuiTable().on('toolbar(' + this._cfg.id + ')', fn)
    }

    tool(fn) {
        this._layuiTable().on('tool(' + this._cfg.id + ')', fn)
    }

    checkbox(fn) {
        this._layuiTable().on('checkbox(' + this._cfg.id + ')', fn)
    }

    reload(cfg) {
        if (!cfg) {
            // 重新加载当前页（自动附带搜索参数）
            this._layuiTable().reload(this._cfg.id)
            return
        }
        const options = {}
        if (this._cfg.page !== false && cfg.page) {
            if (cfg.page < 0) {
                cfg.page += this._page
            }
            options.page = {
                curr: cfg.page
            }
        }
        if (cfg.where !== undefined) {
            options.where = cfg.where
        }
        this._layuiTable().reload(this._cfg.id, options)
    }

    data() {
        return this._layuiTable().cache[this._cfg.id]
    }

    count() {
        return this.data().length
    }

    checkedStatus() {
        return this._layuiTable().checkStatus(this._cfg.id)
    }

    checkedData() {
        return this.checkedStatus().data
    }

    checkedCount() {
        return this.checkedData().length
    }

    _commonCfg() {
        // 公共设置
        const cfg = {
            defaultToolbar: ['filter', 'exports', 'print'],
            done: (res, curr, count) => {
                this._page = curr
                if (count !== 0) return
                // 隐藏checkbox
                $('.laytable-cell-checkbox').hide()
            },
            error: () => {
                // 隐藏checkbox
                $('.laytable-cell-checkbox').hide()
            }
        }

        if (this._cfg.page !== false) { // 如果需要分页
            $.extend(cfg, {
                limits: [10, 20, 50, 100],
                limit: 10,
                page: true,
                request: {
                    limitName: 'size'
                }
            })
        }

        return cfg
    }

    _init() {
        const cfg = this._commonCfg()
        cfg.url = Commons.url(this._cfg.uri)
        $.extend(cfg, this._cfg)
        this._innerTable = this._layuiTable().render(cfg)
        this._cfg = cfg
    }
}

class TreeTable extends Table {
    _layuiTable() {
        return layui.treeTable
    }

    _commonCfg() {
        return  {
            defaultToolbar: ['filter', 'exports', 'print'],
            done: (res) => {
                this._data = res
                if (res && res.length) {
                    $('#expand-btn').removeClass('layui-btn-disabled')
                    $('#fold-btn').removeClass('layui-btn-disabled')
                    return
                }
                // 隐藏checkbox
                $('.laytable-cell-checkbox').hide()
            },
            error: () => {
                // 隐藏checkbox
                $('.laytable-cell-checkbox').hide()
            }
        }
    }

    foldAll() {
        this._innerTable.foldAll()
    }

    expandAll() {
        this._innerTable.expandAll()
    }

    checkedStatus() {
        return this._innerTable.checkStatus(false)
    }

    checkedData() {
        return this._innerTable.checkStatus(false)
    }

    checkedCount() {
        return this.checkedData().length
    }

    data() {
        return this._data
    }

    count() {
        return this.data().length
    }

    filterData(data) {
        this._innerTable.filterData(data)
    }

    clearFilter() {
        this._innerTable.clearFilter()
    }
}

/********************* 页面 ***********************/
class Page extends Module {
    static adminOptions

    static refresh() {
        layui.miniPage.refresh(this.adminOptions)
    }
}

class ListPage extends Page {
    constructor(cfg) {
        super(cfg)

        this._initSearchForm()
        this._initTable()
    }

    _initSearchForm() {
        this._searchForm = new Form({
            selector: '#search-form',
            filter: 'search-form'
        })
        // 搜索
        this._searchForm.submit('search-btn', (data) => {
            if (this._cfg.tree) {
                this._table.filterData(data.field.keyword)
            } else {
                this._table.reload({
                    page: 1,
                    where: data.field
                })
            }
            return false
        })
        // 重置
        this._searchForm.find('#reset-btn').click(() => {
            if (this._cfg.tree) {
                this._searchForm.reset()
                this._table.clearFilter()
            } else {
                Page.refresh()
            }
        })
    }

    _initTable() {
        const cfg = {
            id: 'data-table',
            elem: '#data-table',
            toolbar: '#data-toolbar',
        }
        $.extend(cfg, this._cfg)
        if (cfg.tree) {
            this._table = new TreeTable(cfg)
        } else {
            this._table = new Table(cfg)
        }

        // 监听表格复选框选择
        this._table.checkbox((obj) => {
            this._checkbox(obj)
        })

        // 顶部toolbar监听事件
        this._table.toolbar((obj) => {
            this._toolbar(obj)
        })

        // 每一行的工具条
        this._table.tool((obj) => {
            this._tool(obj)
        })
    }

    _checkbox(obj) {
        const $removeBtn = $('#remove-btn')
        if (obj.type === 'all') { // treetable - 全选\全不选，特殊处理
            if (obj.checked) {
                $removeBtn.removeClass('layui-btn-disabled')
            } else {
                $removeBtn.addClass('layui-btn-disabled')
            }
        } else {
            if (this._table.checkedCount() > 0) {
                $removeBtn.removeClass('layui-btn-disabled')
            } else {
                $removeBtn.addClass('layui-btn-disabled')
            }
        }
    }

    _toolbar(obj) {
        if (obj.event === 'add') { // 监听添加操作
            this._add()
        } else if (obj.event === 'remove') { // 监听删除操作
            this._batchRemove()
        } else if (obj.event === 'fold') {
            this._table.foldAll()
        } else if (obj.event === 'expand') {
            this._table.expandAll()
        }
    }

    _tool(obj) {
        if (obj.event === 'edit') {
            this._edit(obj)
        } else if (obj.event === 'remove') {
            this._remove(obj)
        }
    }

    _add() {
        this._save('添加', this._cfg.savePageUri, () => {
            Page.refresh()
        })
    }

    _edit(obj) {
        const uri = this._cfg.updatePageUri || this._cfg.savePageUri
        this._save('编辑', uri, () => {
            this._table.reload()
        }, obj.data)
    }

    _save(action, uri, end, editData) {
        this._editData = editData
        Layers.openUri(uri, {
            title: action + this._cfg.title,
            success: () => { // 弹框后
                this._initSaveForm()
            },
            end: () => { // 关闭框后
                if (!this._saveSuccess) return

                end()
            }
        })
    }

    _batchRemove() {
        const data = this._table.checkedData()
        const title = '确定要删除选中的【' + data.length + '】条数据么？'
        Layers.confirm(title, () => {
            let ids = data[0].id
            for (let i = 1; i < data.length; i++) {
                ids += ',' + data[i].id
            }
            let prev = false
            if (!this._cfg.tree) { // 不是treeTable
                prev = this._table.checkedStatus().isAll
            }
            this._removeLoadPost(ids, prev)
        })
    }

    _remove(obj) {
        const title = '确定要删除【' + this._removeTitle(obj.data) + '】么？'
        Layers.confirm(title, () => {
            let prev = false
            if (!this._cfg.tree) { // 不是treeTable
                prev = this._table.count() === 1
            }
            this._removeLoadPost(obj.data.id, prev)
        })
    }

    _removeLoadPost(id, loadPrevPage) {
        Ajaxs.loadPost({
            uri: this._cfg.removeUri,
            data: {id},
            success: {
                title: '删除成功',
                after: () => {
                    if (loadPrevPage) {
                        this._table.reload({
                            page: -1
                        })
                    } else {
                        this._table.reload()
                    }
                }
            },
            error: '删除失败'
        })
    }

    _initSaveForm() {
        layui.form.render()

        this._saveForm = new Form({
            selector: '#save-form',
            filter: 'save-form'
        })

        // 自动聚焦
        this._saveForm.find('input[autofocus]').focus()

        // 设置数据
        this._editData && this._saveForm.val(this._editData)
        this._saveSuccess = false

        // 当前弹出层，防止ID被覆盖
        const parentIdx = Layers.index()

        // 监听提交
        this._saveForm.submit('save-btn', (data) => {
            const field = this._onSumbit(data.field)
            if (!field) return
            let formData = field
            let processData
            let contentType
            if (this._saveForm.find('input[type=file]').length > 0) {
                const $form = this._saveForm.$dom()
                $form.attr('method', 'post')
                $form.attr('enctype', 'multipart/form-data')
                formData = new FormData($form[0])
                processData = false
                contentType = false
            }
            Ajaxs.loadPost({
                uri: this._cfg.saveUri,
                data: formData,
                processData,
                contentType,
                success: {
                    title: '保存成功',
                    after: () => {
                        this._saveSuccess = true
                        Layers.close(parentIdx)
                    }
                },
                error: '保存失败'
            })
            return false
        })
    }

    _onSumbit(field) {
        return field
    }

    _removeTitle(data) {
        return data.name
    }
}

class FileInput extends Module {
    // selector/preview{key, uri}/name/accept/multiple
    // uploadExtraData
    constructor(cfg) {
        super(cfg)

        this._$input = $(cfg.selector)
        this._$input.attr('name', cfg.name)
        this._$input.attr('accept', cfg.accept)
        this._$input.attr('multiple', cfg.multiple)

        this._init()
        this._initEvent()
    }

    _init() {
        const uploadUrl = Commons.url(this._cfg.uploadUri)
        const deleteUrl = Commons.url(this._cfg.removeUri)
        const initialPreview = []
        const initialPreviewConfig = []
        if (this._cfg.preview) {
            for (const item of this._cfg.preview) {
                const url = Commons.url(item.uri)
                initialPreview.push(url)
                initialPreviewConfig.push({
                    extra: item.extra,
                    downloadUrl: url
                })
            }
        }

        this._$input.fileinput({
            append: true,
            language: 'zh', // 中文
            uploadAsync: false, // false 同步上传，后台用数组接收，true异步上传，每次上传一个file，会调用多次接口
            showRemove: false, // 不显示删除
            showClose: false, // 不显示右上角的关闭
            showCaption: false, // 不显示新文件的名字
            // allowedFileTypes: ['image'], // 只上传图片类型
            overwriteInitial: false, // 新选择的文件不覆盖旧文件
            fileActionSettings: {
                showUpload: false,
                showDrag: false
            },
            initialPreviewAsData: true,
            initialPreview,
            initialPreviewConfig,
            uploadUrl,
            deleteUrl,
            uploadExtraData: this._cfg.uploadExtraData
        })
    }

    _initEvent() {
        this._$input.on('filebeforedelete', function(event, key, data) { // 删除预览图片之前
            return new Promise((resolve, reject) => {
                Layers.confirm('确定要删除这个文件？', (idx) => {
                    Layers.close(idx)
                    resolve()
                })
            })
        }).on('filedeleted', (event, key, jqXHR, data) => { // 成功删除预览图片
            // key是图片的id
            this._cfg.remove && this._cfg.remove(key)
        }).on('filebatchuploadsuccess', (event, data) => { // 成功批量同步上传图片
            this._cfg.batchUpload && this._cfg.batchUpload(data.response)
        // }).on('filesuccessremove', (event, id) => {
        //
        // }).on('filepreremove', (event, key, index) => {
        //
        })
    }
}

class ImageInput extends Module {
    // selector,name,fileName
    constructor(cfg) {
        super(cfg)

        this._create()
        this._init()
    }

    setImageUri(uri) {
        if (!uri) return
        this._$placeholder.attr('src', Commons.url(uri))
        this._$input.val(uri)
    }

    _create() {
        this._$container = $(this._cfg.selector)
        this._$container.addClass('imageinput imageinput-new')
        this._$container.attr('data-provides', 'imageinput')

        const $placeholderBox = $('<div class="imageinput-new thumbnail">')
        this._$placeholder = $('<img src="images/noimage.png">')
        $placeholderBox.append(this._$placeholder)

        this._$container.append($placeholderBox)
        this._$container.append($('<div class="imageinput-preview imageinput-exists thumbnail"></div>'))
        this._$container.append($('<i class="fa fa-close clear imageinput-exists" data-dismiss="imageinput"></i>'))

        this._$input = $(`<input type="hidden" name="${this._cfg.name}">`)
        this._$container.append(this._$input)

        this._$fileInput = $(`<input type="file" name="${this._cfg.fileName}">`)
        this._$container.append(this._$fileInput)
    }

    _init() {
        const isIE = window.navigator.appName === 'Microsoft Internet Explorer';

        const InnerInput = function (element, options) {
            this.$element = $(element);

            this.$input = this.$element.find(':file');
            if (this.$input.length === 0) return;

            this.name = this.$input.attr('name') || options.name;

            this.$hidden = this.$element.find('input[type=hidden][name="' + this.name + '"]');
            if (this.$hidden.length === 0) {
                this.$hidden = $('<input type="hidden">').insertBefore(this.$input)
            }

            this.$preview = this.$element.find('.imageinput-preview');
            const height = this.$preview.css('height');
            if (this.$preview.css('display') !== 'inline' && height !== '0px' && height !== 'none') {
                this.$preview.css('line-height', height)
            }

            this.original = {
                exists: this.$element.hasClass('imageinput-exists'),
                preview: this.$preview.html(),
                hiddenVal: this.$hidden.val()
            };

            this.listen()
        };

        InnerInput.prototype.listen = function () {
            this.$input.on('change.bs.imageinput', $.proxy(this.change, this));
            $(this.$input[0].form).on('reset.bs.imageinput', $.proxy(this.reset, this));

            this.$element.find('[data-trigger="imageinput"]').on('click.bs.imageinput', $.proxy(this.trigger, this));
            this.$element.find('[data-dismiss="imageinput"]').on('click.bs.imageinput', $.proxy(this.clear, this))
        };

        InnerInput.prototype.change = function (e) {
            const files = e.target.files === undefined ? (e.target && e.target.value ? [{name: e.target.value.replace(/^.+\\/, '')}] : []) : e.target.files;

            e.stopPropagation();

            if (files.length === 0) {
                // 点击取消的时候
                // this.clear();
                return
            }

            this.$hidden.val('');
            this.$hidden.attr('name', '');
            this.$input.attr('name', this.name);

            const file = files[0];

            if (this.$preview.length > 0 && (typeof file.type !== "undefined" ? file.type.match(/^image\/(gif|png|jpeg)$/) : file.name.match(/\.(gif|png|jpe?g)$/i)) && typeof FileReader !== "undefined") {
                const reader = new FileReader();
                const preview = this.$preview;
                const element = this.$element;

                reader.onload = function (re) {
                    const $img = $('<img>');
                    $img[0].src = re.target.result;
                    files[0].result = re.target.result;

                    element.find('.imageinput-filename').text(file.name);

                    // if parent has max-height, using `(max-)height: 100%` on child doesn't take padding and border into account
                    if (preview.css('max-height') !== 'none') {
                        // $img.css('max-height', '140px')
                        // $img.css('max-width', '200px')
                        // $img.css('max-height',
                        //     parseInt(preview.css('max-height'), 10)
                        //     - parseInt(preview.css('padding-top'), 10)
                        //     - parseInt(preview.css('padding-bottom'), 10)
                        //     - parseInt(preview.css('border-top'), 10)
                        //     - parseInt(preview.css('border-bottom'), 10))
                    }

                    preview.html($img);
                    element.addClass('imageinput-exists').removeClass('imageinput-new');

                    element.trigger('change.bs.imageinput', files)
                };

                reader.readAsDataURL(file)
            } else {
                this.$element.find('.imageinput-filename').text(file.name);
                this.$preview.text(file.name);

                this.$element.addClass('imageinput-exists').removeClass('imageinput-new');

                this.$element.trigger('change.bs.imageinput')
            }
        };

        InnerInput.prototype.clear = function (e) {
            if (e) e.preventDefault();

            this.$hidden.val('');
            this.$hidden.attr('name', this.name);
            this.$input.attr('name', '');

            //ie8+ doesn't support changing the value of input with type=file so clone instead
            if (isIE) {
                const inputClone = this.$input.clone(true);
                this.$input.after(inputClone);
                this.$input.remove();
                this.$input = inputClone;
            } else {
                this.$input.val('')
            }

            this.$preview.html('');
            this.$element.find('.imageinput-filename').text('');
            this.$element.addClass('imageinput-new').removeClass('imageinput-exists');

            if (e !== undefined) {
                this.$input.trigger('change');
                this.$element.trigger('clear.bs.imageinput')
            }
        };

        InnerInput.prototype.reset = function () {
            this.clear();

            this.$hidden.val(this.original.hiddenVal);
            this.$preview.html(this.original.preview);
            this.$element.find('.imageinput-filename').text('');

            if (this.original.exists) this.$element.addClass('imageinput-exists').removeClass('imageinput-new');
            else this.$element.addClass('imageinput-new').removeClass('imageinput-exists');

            this.$element.trigger('reset.bs.imageinput')
        };

        InnerInput.prototype.trigger = function (e) {
            this.$input.trigger('click');
            e.preventDefault()
        };

        const old = $.fn.imageinput;

        $.fn.imageinput = function (options) {
            return this.each(function () {
                const $this = $(this)
                let data = $this.data('bs.imageinput');
                if (!data) $this.data('bs.imageinput', (data = new InnerInput(this, options)));
                if (typeof options == 'string') data[options]()
            })
        };

        $.fn.imageinput.Constructor = InnerInput;

        $.fn.imageinput.noConflict = function () {
            $.fn.imageinput = old;
            return this
        };

        $(document).on('click.imageinput.data-api', '[data-provides="imageinput"]', function (e) {
            const $this = $(this);
            if ($this.data('bs.imageinput')) return;
            $this.imageinput($this.data());

            const $target = $(e.target).closest('[data-dismiss="imageinput"],[data-trigger="imageinput"]');
            if ($target.length > 0) {
                e.preventDefault();
                $target.trigger('click.bs.imageinput');
            }
        });
    }
}