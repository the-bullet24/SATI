/*hierMenus.js - Cross-Browser/Full-Window 
* by Peter Belesis. v3.06a 990624 
* Copyright (c) 1999 internet.com LLC. All Rights Reserved. 
* Originally published and documented at http://www.dhtmlab.com/ 
* You may use this code on a public Web site only if this entire 
* copyright notice appears unchanged and you publicly display 
* on the Web page a link to http://www.dhtmlab.com/. 
* 
* Contact pbel@internet.com for all other uses. 
*/ 
 
if(NS4){ 
	Layer.prototype.dw = function(str){ 
		this.document.write(str); 
		this.document.close(); 
	} 
	origWidth = innerWidth; 
	origHeight = innerHeight; 
	onresize = function (){ 
		if (innerWidth==origWidth && innerHeight==origHeight) return; 
		initVars(); 
		NSresized=true; 
		location.reload(); 
	} 
} 
isLoaded = false; 
NSresized = false; 
 
isWin = (navigator.appVersion.indexOf("Win") != -1) 
if (!isWin && !isMac) NSfontOver = false; 
 
mSecsVis = secondsVisible*1000; 
 
imgStr = "<IMG SRC='" + imgSrc + "' WIDTH=" + imgSiz + " HEIGHT=" + imgSiz +" BORDER=0 VSPACE=2 ALIGN=RIGHT>"; 
 
areCreated = false; 
menuLoc = null; 
 
function initVars() { 
	if(areCreated) { 
		for(i=1; i<topCount; i++) { 
			cur = eval("elMenu"+i); 
			clearTimeout(cur.hideTimer); 
			cur.hideTimer=null; 
		} 
		clearTimeout(allTimer); 
	} 
	topCount = 1; 
	areCreated = false; 
	beingCreated = false; 
	isOverMenu = false; 
	currentMenu = null; 
	allTimer = null; 
} 
 
initVars(); 
 
function startIt() { 
	isLoaded = true; 
	menuLoc = window; 
	if (clickKill) { 
		if (NS4) document.captureEvents(Event.MOUSEDOWN); 
		document.onmousedown = clicked; 
	} 
	makeTop();	 
} 
 
function makeTop(){ 
	beingCreated = true; 
 
	while(eval("window.arMenu" + topCount)) { 
		(NS4) ? makeMenuNS(false,topCount) : makeMenuIE(false,topCount); 
		topCount++ 
	} 
 
//	status = (topCount-1) + " Hierarchical Menu Trees Created" 
	areCreated = true; 
	beingCreated = false; 
} 
 
 
function setObj(isChild,parMenu){ 
 
	if (!isChild) { 
		this.menuWidth = this.array[0] ? this.array[0] : menuWidth; 
		this.menuLeft = this.array[1]; 
		this.menuTop = this.array[2]; 
		this.menuFontColor = this.array[3] ? this.array[3] : fntCol; 
		this.menuFontOver = this.array[4] ? this.array[4] : overFnt; 
		this.menuBGColor = this.array[5] ? this.array[5] : backCol; 
		this.menuBGOver = this.array[6] ? this.array[6] : overCol; 
 
		this.menuBorCol = this.array[7] ? this.array[7] : borCol; 
		this.menuSeparatorCol = this.array[8] ? this.array[8] : separatorCol; 
		this.treeParent = this; 
		this.startChild = this; 
 
	} 
	else { 
		this.menuWidth = parMenu.menuWidth; 
 
		this.menuLeft = parMenu.menuLeft; 
		this.menuTop = parMenu.menuTop; 
		this.menuFontColor = parMenu.menuFontColor; 
		this.menuFontOver = parMenu.menuFontOver; 
		this.menuBGColor = parMenu.menuBGColor; 
		this.menuBGOver = parMenu.menuBGOver; 
 
		this.menuBorCol = parMenu.menuBorCol; 
		this.menuSeparatorCol = parMenu.menuSeparatorCol; 
		this.treeParent = parMenu.treeParent; 
 
	} 
 
	this.maxItems = (isChild) ? this.array.length/3 : (this.array.length-9)/3; 
	this.hasParent = isChild; 
	this.itemCount = 0; 
} 
 
 
function makeMenuNS(isChild,menuCount,parMenu,parItem) { 
 
	menu = new Object() 
	menu.lyrid = "elMenu" + menuCount; 
	menu.array = eval("arMenu" + menuCount); 
	menu.menuWidth = menu.array[0] ? menu.array[0] : menuWidth; 
	menu.menuBGColor = menu.array[5] ? menu.array[5] : backCol; 
 
	menu.setObj = setObj; 
	menu.setObj(isChild,parMenu); 
 
	menu.itemStr = ""; 
 
	while (menu.itemCount < menu.maxItems) { 
		menu.itemCount++; 
	//	status = "Creating Hierarchical Menus: " + menuCount + " / " + menu.itemCount; 
		itemName = "item" + menuCount + "_" + menu.itemCount; 
 
		arrayPointer = (isChild) ? (menu.itemCount-1)*3 :((menu.itemCount-1)*3)+9; 
		htmStr = menu.array[arrayPointer]; 
		hasMore = menu.array[arrayPointer + 2]; 
		if (fntBold) htmStr = htmStr.bold(); 
		if (fntItal) htmStr = htmStr.italics(); 
		htmStr = "<FONT FACE='" + fntFam + "' POINT-SIZE=" + fntSiz + ">" + htmStr+ "</FONT>"; 
		htmStrOver = htmStr.fontcolor(menu.menuFontOver); 
		htmStr = htmStr.fontcolor(menu.menuFontColor); 
		htmStr = (hasMore) ? imgStr + htmStr : htmStr; 
		htmStrOver = (hasMore) ? imgStr + htmStrOver : htmStrOver; 
 
		eval("menu."+ itemName + "= new Object()") 
		eval("menu."+ itemName + ".htmStr = htmStr");  
		eval("menu."+ itemName + ".htmStrOver = htmStrOver");  
		menu.itemStr += "<LAYER ID=" + itemName + " WIDTH="+ menu.menuWidth + " BGCOLOR="+ menu.menuBGColor +" LEFT="+ (borWid + itemPad) +">"; 
		menu.itemStr += "<LAYER WIDTH="+ (menu.menuWidth-(borWid*2)-itemPad) +">"+ htmStr +"</LAYER>"; 
		menu.itemStr += "<LAYER LEFT="+ (-itemPad) + " TOP="+ (-itemPad) +"></LAYER>"; 
		menu.itemStr += "</LAYER>"; 
		if (hasMore) { 
			menu.item = "document.elMenu"+ menuCount +".document."+ itemName; 
			makeMenuNS(true,menuCount + "_" + menu.itemCount,menu,menu.item); 
			menu = menu.parentMenu; 
		} 
	} 
	menu.beginStr = "<LAYER ID=elMenu" + menuCount + " LEFT=0 TOP=0 Z-INDEX=500 VISIBILITY=HIDE WIDTH="+ menu.menuWidth +" BGCOLOR="+ menu.menuBorCol +">"; 
	document.write(menu.beginStr + menu.itemStr + "</LAYER>"); 
	eval("elMenu"+menuCount +"=document.elMenu"+menuCount); 
	menuLyr = eval("elMenu"+menuCount); 
	menuLyr.array = menu.array; 
 
	menuLyr.hasParent = isChild; 
	if (!isChild) menuLyr.startChild = menuLyr; 
	 
	itemColl = menuLyr.document.layers; 
	for (var i=0; i<itemColl.length; i++) { 
		it = itemColl[i]; 
		it.menuObj = menu; 
		it.setup = itemSetup; 
		it.setup(i+1,menuLyr.array); 
		menuLyr.lastItem = it; 
	} 
 
	menuLyr.menuObj = menu; 
	menuLyr.setup = menuSetup; 
	menuLyr.setup(isChild,parMenu); 
	menu.parentMenu = parMenu; 
 
} 
 
function makeMenuIE(isChild,menuCount,parMenu) { 
 
	menu = new Object() 
	menu.lyrid = "elMenu" + menuCount; 
	menu.array = eval("arMenu" + menuCount); 
	menu.menuWidth = menu.array[0] ? menu.array[0] : menuWidth; 
	menu.menuBGColor = menu.array[5] ? menu.array[5] : backCol; 
 
	menu.setObj = setObj; 
	menu.setObj(isChild,parMenu); 
 
	menu.itemStr = ""; 
	while (menu.itemCount < menu.maxItems) { 
		menu.itemCount++; 
	//	status = "Creating Hierarchical Menus: " + menuCount + " / " + menu.itemCount; 
 
		itemName = "item" + menuCount + "_" + menu.itemCount; 
 
		arrayPointer = (isChild) ? (menu.itemCount-1)*3 :((menu.itemCount-1)*3)+9; 
		dispText = menu.array[arrayPointer]; 
		linkText = menu.array[arrayPointer + 1]; 
		hasMore = menu.array[arrayPointer + 2]; 
		htmStr = (hasMore) ? imgStr + dispText : dispText; 
		menu.itemStr += "<SPAN ID=" + itemName + " STYLE='width:" + menu.menuWidth + ";background-color:"+ menu.menuBGColor +";"; 
		menu.itemStr += "padding:"+ itemPad +";color:"+ menu.menuFontColor +";"; 
		menu.itemStr += "font-size:"+ fntSiz +"pt;font-weight:"+ ((fntBold) ? "bold;" : "normal;"); 
		menu.itemStr += "font-style:"+ ((fntItal) ? "italic;" : "normal;"); 
		menu.itemStr += "font-family:"+ fntFam +";"; 
		if(menu.itemCount < menu.maxItems)menu.itemStr += "border-bottom:"+ separator + "px "+ menu.menuSeparatorCol +" solid;"; 
		menu.itemStr += "cursor:"+ ((linkText) ? "hand;" : "default;"); 
		menu.itemStr += "'>" + htmStr + "</SPAN><BR>"; 
 
		if (hasMore) { 
			makeMenuIE(true,menuCount + "_" + menu.itemCount,menu); 
			menu = menu.parentMenu; 
		}	 
	} 
 
	menu.beginStr = "<DIV ID=elMenu" + menuCount + " onSelectStart='return false' STYLE='position:absolute;left:0;top:0;z-index:500;"; 
	menu.beginStr += "width:"+ menu.menuWidth +";"; 
	menu.beginStr += "border:"+ borWid +" "+ menu.menuBorCol +" "+ borSty +";"; 
	menu.beginStr += "'>"; 
	document.write( menu.beginStr + menu.itemStr + "</DIV>"); 
	menuLyr = eval("elMenu"+menuCount); 
	menuLyr.array = menu.array; 
 
 
	menuLyr.hasParent = isChild; 
	if (!isChild) menuLyr.startChild = menuLyr; 
 
 
	itemColl = menuLyr.children.tags("SPAN"); 
	for (var i=0; i<itemColl.length; i++) { 
		it = itemColl(i); 
		it.menuObj = menu; 
		it.setup = itemSetup; 
		it.setup(i+1,menuLyr.array); 
		menuLyr.lastItem = it; 
	} 
 
	menuLyr.menuObj = menu; 
	menuLyr.setup = menuSetup; 
	menuLyr.setup(isChild,parMenu); 
	menu.parentMenu = parMenu; 
} 
 
function itemSetup(whichItem,whichArray) { 
	this.onmouseover = itemOver; 
	this.onmouseout = itemOut; 
 
	this.container = (NS4) ? this.parentLayer : this.offsetParent; 
 
	arrayPointer = (this.menuObj.hasParent) ? (whichItem-1)*3 : ((whichItem-1)*3)+9; 
 
	this.linkText = whichArray[arrayPointer + 1]; 
	this.hasMore = whichArray[arrayPointer + 2]; 
 
	if (this.hasMore) { 
		this.child = eval("elMenu" + this.id.substr(4)); 
		this.child.parentMenu = this.container; 
		this.child.parentItem = this; 
	} 
 
	if (this.linkText) { 
		if (NS4) { 
			this.captureEvents(Event.MOUSEUP) 
			this.onmouseup = linkIt; 
		} 
		else { 
			this.onclick = linkIt; 
		} 
	} 
 
	if (NS4) { 
		this.htmStr = eval("this.menuObj."+ this.id + ".htmStr"); 
		this.htmStrOver = eval("this.menuObj."+ this.id + ".htmStrOver"); 
		if (whichItem != 1)	this.top = this.siblingBelow.top + this.siblingBelow.clip.height + separator; 
		else this.top = (borWid + itemPad) 
 
		this.clip.top = this.clip.left = -itemPad; 
		this.clip.right = (this.menuObj.menuWidth-(borWid*2)-itemPad); 
		this.txtLyr = this.document.layers[0]; 
		this.clip.bottom = this.txtLyr.document.height+itemPad; 
		this.dummyLyr = this.document.layers[1]; 
		this.dummyLyr.clip.width = this.clip.width; 
		this.dummyLyr.clip.height = this.clip.height; 
	} 
}	 
 
function menuSetup(hasParent,openCont) { 
	this.onmouseover = menuOver; 
	this.onmouseout = menuOut; 
	 
	this.showIt = showIt; 
	this.keepInWindow = keepInWindow; 
	this.hideTree = hideTree 
	this.hideParents = hideParents; 
	this.hideChildren = hideChildren; 
	this.hideTop = hideTop; 
	this.hasChildVisible = false; 
	this.isOn = false; 
	this.hideTimer = null; 
 
 
	this.menuWidth = this.menuObj.menuWidth; 
	this.menuLeft = this.menuObj.menuLeft; 
	this.menuTop = this.menuObj.menuTop; 
	this.menuFontColor = this.menuObj.menuFontColor; 
	this.menuFontOver = this.menuObj.menuFontOver; 
	this.menuBGColor = this.menuObj.menuBGColor; 
 
	this.childOverlap = (perCentOver != null) ? ((perCentOver/100) * this.menuWidth) : childOverlap; 
	this.currentItem = null; 
	this.hideSelf = hideSelf; 
		 
	this.hasParent = hasParent 
 
	if (NS4) { 
		this.fullHeight = this.lastItem.top + this.lastItem.clip.bottom + borWid; 
		this.clip.top = 0; 
		this.clip.right = this.menuWidth; 
		this.clip.bottom = this.fullHeight; 
	} 
	else { 
		this.fullHeight = this.scrollHeight; 
		this.showIt(false); 
		this.moveTo = moveTo; 
	} 
} 
 
function popUp(menuName,e){ 
	if (NS4 && NSresized) startIt(); 
	if (!isLoaded) return; 
	linkEl = (NS4) ? e.target : event.srcElement; 
	if (clickStart) linkEl.onclick = popMenu; 
	if (!beingCreated && !areCreated) startIt(); 
	linkEl.menuName = (IE4) ? menuName : "document."+menuName;	 
	if (!clickStart) popMenu(e); 
} 
 
function popMenu(e){ 
	if (!isLoaded || !areCreated) return true; 
 
	eType = (NS4) ? e.type : event.type; 
	if (clickStart && eType != "click") return true; 
	hideAll(); 
 
	linkEl = (NS4) ? e.target : event.srcElement; 
	 
	currentMenu = eval(linkEl.menuName); 
	currentMenu.hasParent = false; 
	currentMenu.menuObj.treeParent.startChild = currentMenu; 
	 
	if (IE4) menuLocBod = document.body; 
	xPos = (currentMenu.menuLeft) ? currentMenu.menuLeft : (NS4) ? e.pageX : (event.clientX + menuLocBod.scrollLeft); 
	yPos = (currentMenu.menuTop) ? currentMenu.menuTop : (NS4) ? e.pageY : (event.clientY + menuLocBod.scrollTop); 
 
	currentMenu.moveTo(xPos,yPos); 
	currentMenu.keepInWindow() 
	currentMenu.isOn = true; 
	currentMenu.showIt(true); 
 
	return false; 
} 
 
function menuOver(e) { 
	this.isOn = true; 
	isOverMenu = true; 
	currentMenu = this; 
	if (this.hideTimer) clearTimeout(this.hideTimer); 
} 
 
function menuOut() { 
	if (IE4 && event.srcElement.contains(event.toElement)) return; 
	this.isOn = false; 
	isOverMenu = false; 
	status = ""; 
	if (!clickKill) allTimer = setTimeout("currentMenu.hideTree()",10);   
} 
 
function itemOver(){ 
	if (keepHilite) { 
		if (this.container.currentItem && this.container.currentItem != this) { 
			if (NS4) { 
				this.container.currentItem.bgColor = this.menuObj.menuBGColor; 
				if (NSfontOver) this.container.currentItem.txtLyr.dw(this.container.currentItem.htmStr); 
			} 
			else { 
				with (this.container.currentItem.style) { 
					backgroundColor = this.menuObj.menuBGColor; 
					color = this.menuObj.menuFontColor; 
				} 
			} 
		} 
	} 
 
	if (IE4) { 
		if (event.srcElement.tagName == "IMG") return; 
		this.style.backgroundColor = this.menuObj.menuBGOver; 
		this.style.color = this.menuObj.menuFontOver; 
	} 
	else { 
		this.bgColor = this.menuObj.menuBGOver; 
		if (NSfontOver)	this.txtLyr.dw(this.htmStrOver); 
	} 
 
	status = this.linkText; 
 
	this.container.currentItem = this; 
 
	if (this.container.hasChildVisible) { 
		this.container.hideChildren(this); 
	} 
 
	if (this.hasMore) { 
		horOffset = (this.menuObj.menuWidth - this.container.childOverlap); 
 
		if (NS4) { 
			this.childX = this.container.left + horOffset; 
			this.childY = this.pageY + childOffset; 
		} 
		else { 
			this.childX = this.container.style.pixelLeft + horOffset; 
			this.childY = this.offsetTop + this.container.style.pixelTop + childOffset; 
		} 
 
		this.child.moveTo(this.childX,this.childY); 
		this.child.keepInWindow(); 
		this.container.hasChildVisible = true; 
		this.container.visibleChild = this.child; 
		this.child.showIt(true); 
	} 
} 
 
function itemOut() { 
	if (IE4) { 
    	if (event.srcElement.contains(event.toElement) 
     || (event.fromElement.tagName=="IMG" && event.toElement.contains(event.fromElement))) 
        return; 
		if (!keepHilite) { 
			this.style.backgroundColor = this.menuObj.menuBGColor; 
			this.style.color = this.menuObj.menuFontColor; 
		} 
	} 
	else { 
		if (!keepHilite) { 
			this.bgColor = this.menuObj.menuBGColor; 
			if (NSfontOver) this.txtLyr.dw(this.htmStr); 
		} 
		if (!isOverMenu && !clickKill) { 
			allTimer = setTimeout("currentMenu.hideTree()",10);  
		} 
	} 
} 
 
function moveTo(xPos,yPos) { 
	this.style.pixelLeft = xPos; 
	this.style.pixelTop = yPos; 
} 
 
function showIt(on) { 
	if (NS4) { 
		this.visibility = (on) ? "show" : "hide"; 
		if (keepHilite && this.currentItem) { 
			this.currentItem.bgColor = this.menuBGColor; 
			if (NSfontOver) this.currentItem.txtLyr.dw(this.currentItem.htmStr); 
		} 
	} 
	else { 
		this.style.visibility = (on) ? "visible" : "hidden"; 
		if (keepHilite && this.currentItem) { 
			with (this.currentItem.style) { 
				backgroundColor = this.menuBGColor; 
				color = this.menuFontColor; 
			} 
		} 
	} 
	this.currentItem = null; 
} 
 
function keepInWindow() { 
	scrBars = 20; 
	botScrBar = scrBars; 
	rtScrBar = scrBars; 
	if (NS4) { 
		winRight = (pageXOffset + innerWidth) - rtScrBar; 
		rightPos = this.left + this.menuWidth; 
    
		if (rightPos > winRight) { 
			if (this.hasParent) { 
				parentLeft = this.parentMenu.left; 
				newLeft = ((parentLeft-this.menuWidth) + this.childOverlap); 
				this.left = newLeft; 
			} 
			else { 
				dif = rightPos - winRight; 
				this.left -= dif; 
			} 
		} 
 
		winBot = (pageYOffset + innerHeight) - botScrBar ; 
		botPos = this.top + this.fullHeight; 
 
		if (botPos > winBot) { 
			dif = botPos - winBot; 
			this.top -= dif; 
		} 
		 
		winLeft = pageXOffset; 
		leftPos = this.left; 
 
		if (leftPos < winLeft) { 
			if (this.hasParent) { 
				parentLeft = this.parentMenu.left; 
				newLeft = ((parentLeft+this.menuWidth) - this.childOverlap); 
				this.left = newLeft; 
			} 
			else { 
				this.left = 5; 
			} 
		} 
	} 
	else { 
    	winRight = (document.body.scrollLeft + document.body.clientWidth) - rtScrBar; 
		rightPos = this.style.pixelLeft + this.menuWidth; 
	 
		if (rightPos > winRight) { 
			if (this.hasParent) { 
				parentLeft = this.parentMenu.style.pixelLeft; 
				newLeft = ((parentLeft - this.menuWidth) + this.childOverlap); 
				this.style.pixelLeft = newLeft; 
			} 
			else { 
				dif = rightPos - winRight; 
				this.style.pixelLeft -= dif; 
			} 
		} 
 
		winBot = (document.body.scrollTop + document.body.clientHeight) - botScrBar; 
		botPos = this.style.pixelTop + this.fullHeight; 
 
		if (botPos > winBot) { 
			dif = botPos - winBot; 
			this.style.pixelTop -= dif; 
		} 
		 
		winLeft = document.body.scrollLeft; 
		leftPos = this.style.pixelLeft; 
 
		if (leftPos < winLeft) { 
			if (this.hasParent) { 
				parentLeft = this.parentMenu.style.pixelLeft; 
				newLeft = ((parentLeft+this.menuWidth) - this.childOverlap); 
				this.style.pixelLeft = newLeft; 
			} 
			else { 
				this.style.pixelLeft = 5; 
			} 
		} 
	} 
} 
 
function linkIt() { 
	if (this.linkText.indexOf("javascript")!=-1) eval(this.linkText) 
	else location.href = this.linkText; 
} 
 
function popDown(menuName){ 
	if (!isLoaded || !areCreated) return; 
	whichEl = eval(menuName); 
	whichEl.isOn = false; 
	if (!clickKill) whichEl.hideTop(); 
} 
 
function hideAll() { 
	for(i=1; i<topCount; i++) { 
		temp = eval("elMenu" + i + ".startChild"); 
		temp.isOn = false; 
		if (temp.hasChildVisible) temp.hideChildren(); 
		temp.showIt(false); 
	}	 
} 
 
function hideTree() {  
	allTimer = null; 
	if (isOverMenu) return; 
	if (this.hasChildVisible) { 
		this.hideChildren(); 
	} 
	this.hideParents(); 
} 
 
function hideTop() { 
	whichEl = this; 
	(clickKill) ? whichEl.hideSelf() : (this.hideTimer = setTimeout("whichEl.hideSelf()",mSecsVis)); 
} 
 
function hideSelf() { 
	this.hideTimer = null; 
	if (!this.isOn && !isOverMenu) {  
		this.showIt(false); 
	} 
} 
 
function hideParents() { 
	tempMenu = this; 
	while (tempMenu.hasParent) { 
		tempMenu.showIt(false); 
		tempMenu.parentMenu.isOn = false;		 
		tempMenu = tempMenu.parentMenu; 
	} 
	tempMenu.hideTop(); 
} 
 
function hideChildren(item) { 
	tempMenu = this.visibleChild; 
	while (tempMenu.hasChildVisible) { 
		tempMenu.visibleChild.showIt(false); 
		tempMenu.hasChildVisible = false; 
		tempMenu = tempMenu.visibleChild; 
	} 
 
	if (!this.isOn || !item.hasMore || this.visibleChild != this.child) { 
		this.visibleChild.showIt(false); 
		this.hasChildVisible = false; 
	} 
} 
 
function clicked() { 
	if (!isOverMenu && currentMenu!=null && !currentMenu.isOn) { 
		whichEl = currentMenu; 
		whichEl.hideTree(); 
	} 
} 
 
window.onerror = handleErr; 
 
function handleErr(){ 
	arAccessErrors = ["permission","access"]; 
	mess = arguments[0].toLowerCase(); 
	found = false; 
	for (i=0;i<arAccessErrors.length;i++) { 
		errStr = arAccessErrors[i]; 
		if (mess.indexOf(errStr)!=-1) found = true; 
	} 
	return found; 
} 
 
startIt(); 
 
