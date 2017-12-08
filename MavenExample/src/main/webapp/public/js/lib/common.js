define(function(require, exports, module) {
	// 获取文本内容
	var $ = require('jquery');
	var tpl = require('../app/head.html');
	$('#header').html(tpl);
	var tpl = require('../app/footer.html');
	$('#footer').html(tpl);

});
$.cookie = {
	    get:function(n){
	        var m = document.cookie.match(new RegExp( "(^| )"+n+"=([^;]*)(;|$)"));
	        return !m ? "":m[2];
	    },
	    set:function(name,value,domain,path,minute){
	        var expire = new Date(); 
	        
	        expire.setTime(expire.getTime() + (minute?60*1000 * minute:30*24*60*60*1000));

	        document.cookie = name + "=" + value + "; " + "expires=" + expire.toGMTString()+"; path="+ (path ? path :"/")+ "; "  + (domain ? ("domain=" + domain + ";") : ""); 
	    },
	    del : function(name, domain, path) {
	        document.cookie = name + "=; expires=Mon, 26 Jul 1997 05:00:00 GMT; path="+ (path ? path :"/")+ "; " + (domain ? ("domain=" + domain + ";") : ""); 
	    }
	};