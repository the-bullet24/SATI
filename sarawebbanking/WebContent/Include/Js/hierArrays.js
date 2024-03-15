arMenu1 = new Array(
114,
11,54,
"","",
"","",
"","",
"press room","/pressroom/",0,
"corporate info","/news/corpinfo",0,
"investor relations","/investor/",0
)

arMenu2=new Array(
115,
126,54,
"","",
"","",
"","",
"products","/pss/",1,
"services","/netcare/",1,
"support","/pss/",1,
"solutions","/pss/",1
)

arMenu2_1 = new Array(
"product overview","/pss/prodover/",0,
"a-z product list","/pss/az.html",0,
"library","/pss/library/",0,
"year 2000","/y2k/",0
)

arMenu2_2 = new Array(
"NetworkCare","/networkcare/",0
)

arMenu2_3 = new Array(
"service providers","/pss/support/global_serveprov.html",0,
"enterprise customers","/pss/support/enterprise.html",0,
"oems","/pss/support/oems.html",0
)

arMenu2_4 = new Array(
"VPNWorX for service providers","/vpnworx/vpnworxsp.html",0,
"VPNWorX for enterprises","/vpnworx/vpnworxe.html",0,
"MultiService Core","/gsp/solutions/multiservice/",0,
"Voice Over NetworX","/gsp/solutions/voiceovernetworx/",0,
"Operations Surround","/gsp/solutions/operationssurround/",0,
"Advanced Packet Services","/gsp/solutions/advancedpacket/",0
)

arMenu3=new Array(
114,
222,54,
"","",
"","",
"","",
"americas","/intl/americas.html",0,
"asia/pacific","/intl/asiapac.html",0,
"europe, middle east, africa","/intl/emea.html",0,
"global locator","/intl/locator.html",0
)

arMenu4=new Array(
90,
343,54,
"","",
"","",
"","",
"my profile","/work/create.html",0,
"life@lucent","/work/profile.html",0,
"search jobs","http://206.64.4.112/cgi-bin/parse-file?TEMPLATE=/htdocs/job-search-country.html",0,
"college recruiting","/work/college/why.html",0,
"help","/work/help.html",0
)


arMenu5=new Array(
90,
428,54,
"","",
"","",
"","",
"research","/bell-labs/",0,
"minds","/minds/",0
)

arMenu6=new Array(
92,
518,54,
"","",
"","",
"","",
"site map","/search/sitemap.html",0,
"a-z index","/search/az.html",0,
"contact us","/contact/",0,
"glossary","javascript:DoPopup('/search/glossary/framed.html')",0,
"terms of use","/copyright.html",0,
"privacy statement","/privacy.html",0
)
params = 'toolbar=no,location=no,directories=no,status=no,menubar=yes,scrollbars=yes,resizable=yes,width=500,height=430';
function DoPopup(url)
{
    PopWind = window.open (url, '/SARAWebBanking/Include/Js/PopWind', params);
    PopWind.document.close();
}
//
