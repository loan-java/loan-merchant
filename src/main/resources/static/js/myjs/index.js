	$(function() {
			if ($.cookie('menusf')== "ok") {
				$("#menusf").attr("checked", true);
				$("#sidebar").attr("class", "menu-min");
			} else {
				$("#menusf").attr("checked", false);
				$("#sidebar").attr("class", "");	
			}
		});
		function cmainFrame(){
			var hmain = document.getElementById("mainFrame");
			var bheight = document.documentElement.clientHeight;
			hmain.style.width = '100%';
			hmain.style.height = (bheight  - 51) + 'px';
			var bkbgjz = document.getElementById("bkbgjz");
			bkbgjz .style.height = (bheight  - 41) + 'px';
			
		}
		cmainFrame();
		window.onresize=function(){  
			cmainFrame();
		};
		jzts();

		//保存缩放菜单状态
		function menusf(){
			if(document.getElementsByName('menusf')[0].checked){		
				$.cookie('menusf', 'ok',{ expires: 30 });			
				$("#sidebar").attr("class","menu-min");
			}else{
				$.cookie('menusf', '', { expires: -1 });
				$("#sidebar").attr("class","");
			}
		}
