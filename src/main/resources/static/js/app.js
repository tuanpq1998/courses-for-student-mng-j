document.addEventListener("DOMContentLoaded", function(event) { 
	var menu = document.getElementById("menu");
	
	if (menu) 
		menu.addEventListener("click", function() {
			this.classList.toggle("hover");
			var menuContent = document.getElementById("menu-content");
			menuContent.classList.toggle("show");
		});
	
	});