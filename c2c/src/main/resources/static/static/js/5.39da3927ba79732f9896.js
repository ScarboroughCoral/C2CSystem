webpackJsonp([5],{"7IUR":function(e,t,s){(e.exports=s("BkJT")(!1)).push([e.i,"\ndiv.info-container {\n  width: 750px;\n  padding: 20px;\n  border: 2px solid #F2F2F0;\n  margin-bottom: 10px;\n}\n.edit-input {\n  padding-right: 100px;\n}\n.cancel-btn {\n  position: absolute;\n  right: 15px;\n  top: 10px;\n}\n.el-row {\n  margin-bottom: 20px;\n}\n.el-row:last-child {\n    margin-bottom: 0;\n}\n.bg-purple {\n  background: #d3dce6;\n}\n.grid-content {\n  border-radius: 4px;\n  min-height: 36px;\n}\n.el-header {\n  text-align: center;\n}\n.el-header p {\n    text-align: left;\n}\n.result-title {\n  font-size: 30px;\n}\n.vote-result-footer {\n  display: -webkit-box;\n  display: -ms-flexbox;\n  display: flex;\n  -ms-flex-pack: distribute;\n      justify-content: space-around;\n  background: #EBEEF5;\n}\n",""])},NS8l:function(e,t,s){"use strict";t.c=function(){return Object(i.a)({url:"/address/province",method:"get"})},t.a=function(e){return Object(i.a)({url:"/address/getCity",method:"get",params:{provinceId:e}})},t.b=function(e){return Object(i.a)({url:"/address/getDistrict",method:"get",params:{cityId:e}})};var i=s("vLgD")},Z6bo:function(e,t,s){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=s("uAC3"),o=s.n(i),a=s("0xDb"),n=s("I2ex"),r=s("NS8l"),l={name:"vote",data:function(){return{uploadDialogVisible:!1,pickerOptions2:{shortcuts:[{text:"最近一周",onClick:function(e){var t=new Date,s=new Date;s.setTime(s.getTime()-6048e5),e.$emit("pick",[s,t])}},{text:"最近一个月",onClick:function(e){var t=new Date,s=new Date;s.setTime(s.getTime()-2592e6),e.$emit("pick",[s,t])}},{text:"最近三个月",onClick:function(e){var t=new Date,s=new Date;s.setTime(s.getTime()-7776e6),e.$emit("pick",[s,t])}}]},value4:[new Date(2e3,10,10,10,10),new Date(2e3,10,11,10,10)],value5:"",options:[],selectedOptions:[],selectedOptions2:[],dialogImageUrl:"",dialogVisible:!1,usersMeeting:[],terminalsMeeting:[],unclickable:!1,clickabletext:"确认",tableHeader:[],provinces:[],cities:[],districts:[],imgList:[],houseForm:{userId:void 0,price:0,houseTypeID:void 0,houseStateID:1,holdNum:0,houseDesc:"",houseArea:0,houseAddr:"",provinceId:void 0,cityId:void 0,districtId:void 0,rentStartTime:void 0,rentEndTime:void 0},editForm:{},houseTypes:[{value:1,label:"海景房测试"}],nowRecord:{},list:null,listLoading:!0,searchInputPlaceholder:"请输入房源类型",recordDialogStatus:"voteResult",recordDialogFormVisible:!1,recordTextMap:{voteResult:"选票结果"},responseData:[],dialogFormVisible:!1,dialogStatus:"addHouse",listQuery:{page:1,limit:10,importance:void 0},textMap:{addHouse:"发布房源",editHouse:"编辑房源"},userID:void 0}},filters:{statusFilter:function(e){return{"可以租用":"success","已经撤回":"info",deleted:"danger"}[e]||"primary"}},created:function(){this.userID=o.a.get("userID"),this.getList(),this.houseForm.userId=this.userID,this.getAllProvince()},methods:{handleUpload:function(e){var t=this,s=e.file,i=new FormData;return i.append("img",s),i.append("houseId",this.nowRecord.houseId),Object(n.l)(i).then(function(e){e.success?t.$message.success(e.message):t.$message.error(e.message)}),console.log(this.imgList),!0},handleHousePic:function(e,t){this.uploadDialogVisible=!0,this.nowRecord=t},getAllProvince:function(){var e=this;this.provinces=[],Object(r.c)().then(function(t){for(var s=0;s<t.data.length;s++){var i=t.data[s];e.provinces.push({value:i.provinceId,label:i.provinceDesc})}})},chooseCity:function(e){var t=this;this.cities=[],Object(r.a)(e).then(function(e){for(var s=0;s<e.data.length;s++){var i=e.data[s];t.cities.push({value:i.cityId,label:i.cityDesc})}})},chooseDistrict:function(e){var t=this;this.districts=[],Object(r.b)(e).then(function(e){for(var s=0;s<e.data.length;s++){var i=e.data[s];t.districts.push({value:i.districtId,label:i.districtDesc})}})},tableDataSort:function(e,t){return parseInt(e)-parseInt(t)},handlePublish:function(){var e=this;this.houseForm.rentStartTime=this.value5[0],this.houseForm.rentEndTime=this.value5[1],Object(n.h)(this.houseForm).then(function(t){t.success&&(e.$message.success(t.message),e.dialogFormVisible=!1,e.getList())}),this.getList()},handleHouseEdit:function(e,t){var s=this;this.getHouseTypes(),this.dialogStatus="editHouse",this.dialogFormVisible=!0,this.nowRecord=t;var i=this;Object(n.f)(t.houseId).then(function(e){if(e.success){var t=e.data;s.houseForm.rentStartTime=s.value5[0],s.houseForm.rentEndTime=s.value5[1],i.houseForm.price=t.price,i.houseForm.houseArea=t.area,i.editForm={price:i.houseForm.price,area:i.houseForm.houseArea,houseDesc:i.houseForm.houseDesc,houseAddr:i.houseForm.houseDesc,rentStartTime:i.houseForm.rentStartTime,rentEndTime:i.houseForm.rentEndTime}}})},handleEditSubmit:function(){var e=this;Object(n.j)(this.nowRecord.houseId,this.editForm).then(function(t){e.dialogFormVisible=!1,t.success?e.$message.success(t.message):e.$message.error(t.message)})},selected:function(e){this.tableData=e.results,this.tableHeader=e.header,this.traditionalCreateForm.rowNumber=this.tableData.length+1,this.traditionalCreateForm.columnNumber=this.tableHeader.length},handleHouse:function(){this.dialogStatus="addHouse",this.dialogFormVisible=!0,this.getHouseTypes()},getHouseTypes:function(){var e=this;Object(n.e)().then(function(t){if(e.houseTypes=[],t.success)for(var s=0;s<t.data.length;s++){var i=t.data[s];e.houseTypes.push({value:i.houseTypeId,label:i.houseType})}})},getList:function(){var e=this;this.listLoading=!0,Object(n.i)(this.userID).then(function(t){var s=t.data;e.responseData=s,e.list=s.map(function(t){return e.$set(t,"edit",!1),t.originalTitle=t.title,t}),e.listLoading=!1})},formatJson:function(e,t){return t.map(function(t){return e.map(function(e){return"timestamp"===e?Object(a.a)(t[e]):t[e]})})},handleRemove:function(e,t){console.log(e,t)},handlePictureCardPreview:function(e){return this.dialogImageUrl=e.url,this.dialogVisible=!0,!0},handleCollect:function(e,t){var s=this;this.$confirm("确定要撤回房源吗, 是否继续?","撤回房源",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){var e={houseId:t.houseId,houseStateId:3};Object(n.k)(e).then(function(e){e.success&&(s.$message.success(e.message),s.getList())})}).catch(function(){s.$message({type:"info",message:"撤回失败！"})})},handleRepublic:function(e,t){var s=this,i={houseId:t.houseId,houseStateId:1};Object(n.k)(i).then(function(e){e.success&&(s.$message.success(e.message),s.getList())})}}},u={render:function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"app-container calendar-list-container"},[s("div",{staticClass:"filters"},[s("div",{staticClass:"filter-block vote-create"},[s("el-tag",[e._v("房源管理")]),e._v(" "),s("div",{staticClass:"filter-container double-filter-container"},[s("el-button",{attrs:{type:"primary",icon:"el-icon-tickets"},on:{click:e.handleHouse}},[e._v("发布房源")])],1)],1)]),e._v(" "),s("div",{staticClass:"filter-bar"}),e._v(" "),s("el-table",{staticStyle:{width:"100%"},attrs:{data:e.responseData,border:"",fit:"","highlight-current-row":""}},[s("el-table-column",{attrs:{align:"center",type:"selection",width:"55"}}),e._v(" "),s("el-table-column",{attrs:{align:"center",label:"序号",type:"index",width:"55"}}),e._v(" "),s("el-table-column",{attrs:{align:"center",label:"房源类型描述"},scopedSlots:e._u([{key:"default",fn:function(t){return[s("span",[e._v(e._s(t.row.houseTypeDes))])]}}])}),e._v(" "),s("el-table-column",{attrs:{align:"center",label:"房源简介"},scopedSlots:e._u([{key:"default",fn:function(t){return[s("span",[e._v(e._s(t.row.houseDecs))])]}}])}),e._v(" "),s("el-table-column",{attrs:{align:"center",label:"房源所在地"},scopedSlots:e._u([{key:"default",fn:function(t){return[s("span",[e._v(e._s(t.row.provinceDesc+t.row.cityDesc+t.row.districtDesc))])]}}])}),e._v(" "),s("el-table-column",{attrs:{align:"center",label:"租金"},scopedSlots:e._u([{key:"default",fn:function(t){return[s("el-tag",{attrs:{type:"danger"}},[e._v("¥"+e._s(t.row.price))])]}}])}),e._v(" "),s("el-table-column",{attrs:{align:"center",label:"状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[s("el-tag",{attrs:{type:e._f("statusFilter")(t.row.houseStateDes)}},[e._v(e._s(t.row.houseStateDes))])]}}])}),e._v(" "),s("el-table-column",{attrs:{align:"center",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[s("el-button",{attrs:{type:"success",size:"mini"},on:{click:function(s){e.handleHousePic(t.$index,t.row)}}},[e._v("上传图片")]),e._v(" "),s("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(s){e.handleHouseEdit(t.$index,t.row)}}},[e._v("编辑")]),e._v(" "),"可以租用"==t.row.houseStateDes?s("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(s){e.handleCollect(t.$index,t.row)}}},[e._v("撤回")]):e._e(),e._v(" "),"已经撤回"==t.row.houseStateDes?s("el-button",{attrs:{size:"mini",type:"success"},on:{click:function(s){e.handleRepublic(t.$index,t.row)}}},[e._v("发布")]):e._e()]}}])})],1),e._v(" "),s("el-dialog",{attrs:{title:e.textMap[e.dialogStatus],visible:e.dialogFormVisible,top:"5vh"},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[s("el-form",{attrs:{"label-position":"left","label-width":"100px"}},[s("el-form-item",{attrs:{required:"",label:"房源类型"}},[s("el-select",{attrs:{placeholder:"请选择"},model:{value:e.houseForm.houseTypeID,callback:function(t){e.$set(e.houseForm,"houseTypeID",t)},expression:"houseForm.houseTypeID"}},e._l(e.houseTypes,function(e){return s("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}))],1),e._v(" "),s("el-form-item",{attrs:{required:"",label:"租金"}},[s("el-input-number",{attrs:{precision:2,step:.01},model:{value:e.houseForm.price,callback:function(t){e.$set(e.houseForm,"price",t)},expression:"houseForm.price"}}),s("el-tag",{attrs:{type:"danger"}},[e._v("元")])],1),e._v(" "),s("el-form-item",{attrs:{required:"",label:"房间面积"}},[s("el-input-number",{attrs:{precision:2,step:.01},model:{value:e.houseForm.houseArea,callback:function(t){e.$set(e.houseForm,"houseArea",t)},expression:"houseForm.houseArea"}})],1),e._v(" "),s("el-form-item",{attrs:{required:"",label:"容纳人数"}},[s("el-input-number",{attrs:{precision:2,step:1},model:{value:e.houseForm.holdNum,callback:function(t){e.$set(e.houseForm,"holdNum",t)},expression:"houseForm.holdNum"}})],1),e._v(" "),s("el-form-item",{attrs:{required:"",label:"出租时间"}},[s("el-date-picker",{attrs:{type:"datetimerange","picker-options":e.pickerOptions2,"range-separator":"至","start-placeholder":"开始日期","end-placeholder":"结束日期",align:"right"},model:{value:e.value5,callback:function(t){e.value5=t},expression:"value5"}})],1),e._v(" "),s("el-form-item",{attrs:{required:"",label:"房源所在地"}},[s("el-select",{attrs:{placeholder:"省级地区"},on:{change:e.chooseCity},model:{value:e.houseForm.provinceId,callback:function(t){e.$set(e.houseForm,"provinceId",t)},expression:"houseForm.provinceId"}},e._l(e.provinces,function(e){return s("el-option",{key:e.id,attrs:{label:e.label,value:e.value}})})),e._v(" "),s("el-select",{attrs:{placeholder:"市级地区"},on:{change:e.chooseDistrict},model:{value:e.houseForm.cityId,callback:function(t){e.$set(e.houseForm,"cityId",t)},expression:"houseForm.cityId"}},e._l(e.cities,function(e){return s("el-option",{key:e.id,attrs:{label:e.label,value:e.value}})})),e._v(" "),s("el-select",{attrs:{placeholder:"区级地区"},model:{value:e.houseForm.districtId,callback:function(t){e.$set(e.houseForm,"districtId",t)},expression:"houseForm.districtId"}},e._l(e.districts,function(e){return s("el-option",{key:e.id,attrs:{label:e.label,value:e.value}})}))],1),e._v(" "),s("el-form-item",{attrs:{required:"",label:"房源简介"}},[s("el-input",{attrs:{type:"textarea",autosize:{minRows:2,maxRows:4},placeholder:"请输入内容"},model:{value:e.houseForm.houseDesc,callback:function(t){e.$set(e.houseForm,"houseDesc",t)},expression:"houseForm.houseDesc"}})],1)],1),e._v(" "),s("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[s("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取消")]),e._v(" "),"addHouse"===e.dialogStatus?s("el-button",{attrs:{type:"primary",disabled:e.unclickable},on:{click:e.handlePublish}},[e._v(e._s(e.clickabletext))]):s("el-button",{attrs:{type:"primary",disabled:e.unclickable},on:{click:e.handleEditSubmit}},[e._v(e._s(e.clickabletext))])],1)],1),e._v(" "),s("el-dialog",{attrs:{title:"点击按钮立即上传房间图片",visible:e.uploadDialogVisible},on:{"update:visible":function(t){e.uploadDialogVisible=t}}},[s("el-form",[s("el-form-item",{attrs:{required:"",label:"房间图片"}},[s("el-upload",{attrs:{action:"https://jsonplaceholder.typicode.com/posts/","list-type":"picture-card","on-preview":e.handlePictureCardPreview,"on-remove":e.handleRemove,"file-list":e.imgList,multiple:!0,"http-request":e.handleUpload,"show-file-list":!0}},[s("i",{staticClass:"el-icon-plus"})]),e._v(" "),s("el-dialog",{attrs:{visible:e.dialogVisible},on:{"update:visible":function(t){e.dialogVisible=t}}},[s("img",{attrs:{width:"100%",src:e.dialogImageUrl,alt:""}})])],1)],1)],1)],1)},staticRenderFns:[]};var c=s("/Xao")(l,u,!1,function(e){s("gVAf")},null,null);t.default=c.exports},gVAf:function(e,t,s){var i=s("7IUR");"string"==typeof i&&(i=[[e.i,i,""]]),i.locals&&(e.exports=i.locals);s("8bSs")("777b4cfb",i,!0)}});