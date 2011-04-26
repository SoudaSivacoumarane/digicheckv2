


_menuCloseDelay=500;           // The time delay for menus to remain visible on mouse out
_menuOpenDelay=150;           // The time delay before menus open on mouse over
_followSpeed=5;                // Follow scrolling speed
_followRate=40;                // Follow scrolling Rate
_subOffsetTop=10;              // Sub menu top offset
_subOffsetLeft=-10;            // Sub menu left offset
_scrollAmount=3;               // Only needed for Netscape 4.x
_scrollDelay=20;               // Only needed for Netcsape 4.x



with(menuStyle=new mm_style()){
	onbgcolor="#cccccc";
	oncolor="#ffffff";
	offbgcolor="#edeaea";
	offcolor="#515151";
	bordercolor="#296488";
	borderstyle="solid";
	borderwidth=1;
	separatorcolor="#2D729D";
	separatorsize="1";
	padding=5;
	fontsize="120%";
	fontstyle="normal";
	fontfamily="Tahoma";
	pagecolor="black";
	pagebgcolor="#82B6D7";
	headercolor="#000000";
	headerbgcolor="#ffffff";
	subimage="";
	subimagepadding="2";
	overfilter="Fade(duration=0.1);Alpha(opacity=100);Shadow(color='#777777', Direction=135, Strength=5)";
	outfilter="randomdissolve(duration=0.1)";
}

with(milonic=new menuname('catalogos')){
	style=menuStyle;
	aI('text=Bancos;url=bancos.xhtml;');
	aI('text=Divisas;url=divisas.xhtml;');
}

with(milonic=new menuname('reportes')){
	style=menuStyle;
	aI('text=Mensual;url=reporte_mensual.xhtml;');
	aI('text=Diario;url=reporte_diario.xhtml;');
}

drawMenus();
