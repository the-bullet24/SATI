/*
Text Link/Image Map Tooltip Script- 
� Dynamic Drive (www.dynamicdrive.com)
For full source code, installation instructions,
100's more DHTML scripts, and Terms Of
Use, visit dynamicdrive.com
*/

if (!document.layers && !document.all)
	event="test"

function showtip(current,e,text)
{
	if (document.all)
	{
		thetitle = text.split('<br>');
		if (thetitle.length > 1)
		{
			thetitles = '';
			for (i=0;i<thetitle.length;i++)
				thetitles+=thetitle[i];
			current.title = thetitles
		}
		else
			current.title = text;
	}
	else if (document.layers)
	{
		document.tooltip.document.write('<layer bgColor="white" style="border:1px solid black;font-size:12px;">'+text+'</layer>');
		document.tooltip.document.close();
		document.tooltip.left=e.pageX+5;
		document.tooltip.top=e.pageY+5;
		document.tooltip.visibility="show";
	}
}

function hidetip(){
	if (document.layers)
	{
		document.tooltip.visibility="hidden";
	}
}
<div id="tooltip" style="position:absolute;visibility:hidden"></div>
