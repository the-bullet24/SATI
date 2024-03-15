package CosapiSoft.SARAWebBanking;

/**
 * This type was created in VisualAge.
 */
public final class Table {
/**
 * Table constructor comment.
 */
public Table() {
	super();
}
public static final String[] getColumnsTactdat() {
	String columns[] = {"CODACT", "TXTACT"};
	return columns;
}
public static final String[] getColumnsTactprf() {
	String columns[] = {"CODPRF", "CODACT"};
	return columns;
}
/*public static final String[] getColumnsTbradat() {
	String columns[] = {"CODBRA", "TXTBRA", "TXTCTY", "TXTADR","CODLIM","FLGBLK","TXTPLZ","TXTSUC"};
	return columns;
}*/
public static final String[] getColumnsTbrainf() {
	String columns[] = {"CODBRA", "NUMSUM", "FLGBLKBRA", "DAYPRGTRA", "BANDEF", "HOUINI", "HOUFIN","BANPNA","BANPJU","TYPENCRIPCION"};
	return columns;
}


public static final String[] getColumnsTcoldat() {
	String columns[] = {"TYPPER", "COLBCK", "COLTIT","COLSUBTIT", "COLROW1", "COLROW2"};
	return columns;
}

public static final String[] getColumnsTafidat() {
	String columns[] = {"TIPAFI", "NUMTAR", "NUMSEC","TYPDOC", "NUMDOC", "FECNAC", "SEXPER", "EMAIL", "CODDOC", "CODSER", "NUMSER", "DESAFIL", "CTAPRO", "NOMBEN"};
	return columns;
}

public static final String[] getColumnsTbandat() {
	String columns[] = {"TYPPER", "DATBEG", "DATEND", "FILNAM"};
	return columns;
}
public static final String[] getColumnsTbrausr() {
	String columns[] = {"CODMOD", "CODUSR", "CODPRF"};
	return columns;
}
public static final String[] getColumnsTmendat() {
	String columns[] = {"CODPER", "CODOPT", "OPTMEN", "TXTMEN","CODPAD", "ORDMEN", "CODTAR", "FLGACT"};
	return columns;
}
/*public static final String[] getColumnsTchndat() {
	String columns[] = {"CODCHN", "TXTCHN"};
	return columns;
}*/
public static final String[] getColumnsTcladat() {
	String columns[] = {"CODCLA", "TXTCLA", "SESNAM"};
	return columns;
}
public static final String[] getColumnsTcommsg() {
	String columns[] = {"CODMSGCOM", "NUMSEQ", "CODDIC", "TXTMSGCOM", "NUMBEGPOS", "NUMLON"};
	return columns;
}

public static final String[] getColumnsTmsghst() {
	String columns[] = {"CODAPLICACION", "CODRETORNO", "DESMSGTOLD", "DESMSGFRONT"};
	return columns;
}

public static final String[] getColumnsTdepdat() {
	String columns[] = {"F02_CUBIGEO", "F02_CDEPARTAMENTO", "F02_DEPARTAMENTO", "F02_CPROVINCIA", "F02_PROVINCIA","F02_CDISTRITO", "F02_DISTRITO","F02_ZONAL","F02_UBIGEO_BNSIF50", "F02_CREGION" };
	return columns;
}

public static final String[] getColumnsTofidat() {
	String columns[] = {"COD_UBIGEO", "COD_DEP4", "F01_AOFICINA", "F01_COFICINA", "FLG_MONEDA" };
	return columns;
}
/*public static final String[] getColumnsTcredat() {
	String columns[] = {"NUMCRE", "CODPRG", "CODLIN", "AMOCRE", "CODCUR", "TYPCRE", "NUMDOC", "DATCRE", "DATREGCRE", "TIMREGCRE", "CODNOD", "CODUSR1", "CODPAT", "VERPAT", "TXTPIZ", "AMOINT"};
	return columns;
}
public static final String[] getColumnsTctrfas() {
	String columns[] = {"CODCHN", "CODTRA", "CODDIC", "CODFAS", "CODCLAALT", "CODMETALT"};
	return columns;
}*/
/*public static final String[] getColumnsTctrmetarg() {
	String columns[] = {"CODCHN", "CODTRA", "CODDIC", "CODFAS", "NUMSEQ", "NUMSEQARG", "TXTARG"};
	return columns;
}
public static final String[] getColumnsTctrmetargalt() {
	String columns[] = {"CODCHN", "CODTRA", "CODDIC", "CODFAS", "NUMSEQARG", "TXTARG"};
	return columns;
}*/
/*
public static final String[] getColumnsTctrsch() {
	String columns[] = {"CODCHN", "CODTRA", "CODDIC", "CODFAS", "NUMSEQ", "CODCLA", "CODMET"};
	return columns;
}*/
public static final String[] getColumnsTctrtra() {
	String columns[] = {"CODTRA", "CODDIC", "TXTDES", "TXTCTR","FLGNUL","CODHLP"};
	return columns;
}
public static final String[] getColumnsTcurdat() {
	String columns[] = {"CODCUR", "TXTCURLON", "TXTCURSHT", "CODCURBCR", "CODCURSWI", "CODCURSYM", "AMOARBPUR", "AMOARBSAL", "FLGCURDEC"};
	return columns;
}
public static final String[] getColumnsTdicdat() {
	String columns[] = {"CODDIC", "TXTDES", "NUMLON", "IDEDIC", "TXTFOR", "TIPDAT", "TXTHLP"};
	return columns;
}
/*
public static final String[] getColumnsTentdat() {
	String columns[] = {"CODENT", "TXTDES"};
	return columns;
}*/
public static final String[] getColumnsTgrpmsg() {
	String columns[] = {"CODGRP", "TXTDES"};
	return columns;
}

public static final String[] getColumnsTgrppan() {
	String columns[] = {"CODGRP", "TXTDES"};
	return columns;
}

public static final String[] getColumnsTgrptra() {
	String columns[] = {"CODGRP", "TXTDES"};
	return columns;
}
public static final String[] getColumnsThlpdat() {
	String columns[] = {"CODHLP", "TXTHLP", "CODFATHLP"};
	return columns;
}
public static final String[] getColumnsThlpdet() {
	String columns[] = {"CODHLP", "NUMSEQ", "TXTHLP", "TXTHLPDAT", "CODHLPDAT"};
	return columns;
}
public static final String[] getColumnsTjoudic() {
	String columns[] = {"CODTRA", "CODDIC", "IDEFLD"};
	return columns;
}
/*
public static final String[] getColumnsTlindat() {
	String columns[] = {"CODPRG", "CODLIN", "LINDES"};
	return columns;
}*/
public static final String[] getColumnsTlimdat() {
	String columns[] = {"TYPPER", "TRAHST","LIMOPEINF", "LIMOPESUP", "LIMDAYINF", "LIMDAYSUP", "CODCUR"};
	return columns;
}
public static final String[] getColumnsTtrahor() {
	String columns[] = {"TRAHST","CODDIA", "HORINI", "HORFIN"};
	return columns;
}
/*
public static final String[] getColumnsTlimdet() {
	String columns[] = {"CODLIM", "CODCUR", "LIMMAX", "LIMMIN"};
	return columns;
}*/
public static final String[] getColumnsTmetarg() {
	String columns[] = {"CODTRA", "CODFAS", "CODALT", "NUMSEQ", "NUMSEQARG", "TXTARG"};
	return columns;
}
public static final String[] getColumnsTmetargalt() {
	String columns[] = {"CODTRA", "CODFAS", "CODALT", "NUMSEQARG", "TXTARG"};
	return columns;
}
public static final String[] getColumnsTmetdat() {
	String columns[] = {"CODCLA", "CODMET", "TXTMET", "TXTDES"};
	return columns;
}
public static final String[] getColumnsTmoddat() {
	String columns[] = {"CODMOD", "TXTMOD"};
	return columns;
}
public static final String[] getColumnsTmsgdat() {
	String columns[] = {"CODGRP", "CODMSG", "DESMSG", "IDEMSG"};
	return columns;
}
public static final String[] getColumnsTmsgpan() {
	String columns[] = {"CODGRP", "CODMSG", "DESMSG", "IDEMSG"};
	return columns;
}
/*
public static final String[] getColumnsTnoddat() {
	String columns[] = {"CODNOD", "TXTNAM"};
	return columns;
}
public static final String[] getColumnsTnodpat() {
	String columns[] = {"CODPAT", "VERPAT", "CODNOD", "NUMSEC"};
	return columns;
}
public static final String[] getColumnsTnodtas() {
	String columns[] = {"CODNOD", "CODTAS"};
	return columns;
}
public static final String[] getColumnsTpatdat() {
	String columns[] = {"CODPAT", "VERPAT", "PATDES"};
	return columns;
}
public static final String[] getColumnsTprdsub() {
	String columns[] = {"IDEACC", "IDESUB","TXTDES"};
	return columns;
}*/
public static final String[] getColumnsTprfbra() {
	String columns[] = {"CODPRF", "TXTPRF"};
	return columns;
}
public static final String[] getColumnsTprfdat() {
	String columns[] = {"CODPRF", "TXTPRF"};
	return columns;
}
public static final String[] getColumnsTprfgrp() {
	String columns[] = {"CODPRF", "CODGRP"};
	return columns;
}/*
public static final String[] getColumnsTprflim() {
	String columns[] = {"CODCUR", "CODPRF", "LIMSUP", "LIMINF"};
	return columns;
}
public static final String[] getColumnsTprftar() {
	String columns[] = {"CODTRA", "CODPRF", "CODCHN", "FLGADDRES"};
	return columns;
}
public static final String[] getColumnsTprgdat() {
	String columns[] = {"CODPRG", "PRGDES"};
	return columns;
}
public static final String[] getColumnsTprodat() {
	String columns[] = {"CODCLA", "CODPRO", "TXTPRO", "TXTDES"};
	return columns;
}*/
public static final String[] getColumnsTschtra() {
	String columns[] = {"CODTRA", "CODFAS", "CODALT", "NUMSEQ", "CODCLA", "CODMET", "ORDSND"};
	return columns;
}
/*
public static final String[] getColumnsTsubprd() {
	String columns[] = {"IDEACC", "IDESUB","TXTDES"};
	return columns;
}
public static final String[] getColumnsTtabdat() {
	String columns[] = {"CODTAB", "TXTDES"};
	return columns;
}
public static final String[] getColumnsTtasdat() {
	String columns[] = {"CODTAS", "TXTDES","TYPTAS"};
	return columns;
}*/
public static final String[] getColumnsTtradat() {
	String columns[] = {"CODTRA", "CODGRP", "TXTTRA", "TXTPRCGDE", "FLGJOU","FLGENB","HOUINI","HOUFIN","TIMOUT1","TIMOUT2"};
	return columns;
}
public static final String[] getColumnsTtrafas() {
	String columns[] = {"CODTRA", "CODFAS", "CODALT", "CODCLAALT", "CODMETALT"};
	return columns;
}
public static final String[] getColumnsTusrdat() {
	String columns[] = {"CODUSR", "TXTNAM", "TXTPAS", "CODBRA", "USRMAIL", "DATBEG", "TIMBEG", "CODLIM", "TXTUSU", "TXTSEC", "HISPWD", "TXTWKS"};
	return columns;
}
public static final String[] getColumnsTusrmod() {
	String columns[] = {"CODUSR", "CODMOD"};
	return columns;
}
/*
public static final String[] getColumnsTusrnod() {
	String columns[] = {"CODNOD", "CODUSR"};
	return columns;
}*/
public static final String[] getColumnsTusrprf() {
	String columns[] = {"CODUSR", "CODMOD", "CODPRF"};
	return columns;
}
public static final String[] getColumnsTusrsgn() {
	String columns[] = {"CODUSR", "CODMOD", "CODPRF"};
	return columns;
}
public static final String[] getColumnsTcajdat() {
	String columns[] = {"CODCAJ", "NOMCAJ", "CODCUR", "CODUSR", "CODBRA", "CODLIM","FLGBLK"};
	return columns;
}public static final String[] getColumnsTfacacc() {
	String columns[] = {"CODORG","CODMOD", "CODUSR", "FLGCNS", "FLGTRF", "LIMTRF", "FLGPAY", "LIMPAY","FLGMON","STRTIMMON","ENDTIMMON","FLGTUE","STRTIMTUE","ENDTIMTUE","FLGWED","STRTIMWED","ENDTIMWED","FLGTHU","STRTIMTHU","ENDTIMTHU","FLGFRI","STRTIMFRI","ENDTIMFRI","FLGSAT","STRTIMSAT","ENDTIMSAT","FLGSUN","STRTIMSUN","ENDTIMSUN"};
	return columns;
}public static final String[] getColumnsTlstdet() {
	String columns[] = {"CODORG", "CODLST", "CODEMP", "TXTNAM", "LSTNAM", "FAMNAM", "AMOEMP", "FLGACT", "NUMACC"};
	return columns;
}public static final String[] getColumnsTorgdat() {
	String columns[] = {"CODORG", "TXTEMP", "FLGMAN"};
	return columns;
}public static final String[] getColumnsTorglst() {
	String columns[] = {"CODORG","CODLST", "TXTLST"};
	return columns;
}public static final String[] getColumnsTorgnot() {
	String columns[] = {"CODORG", "CODNOT", "TXTNAM","TXTEML"};
	return columns;
}public static final String[] getColumnsTtotcom() {
	String columns[] = {"CODTOTCOM", "NOMTOTCOM"};
	return columns;
}public static final String[] getColumnsTtotcomdet() {
	String columns[] = {"CODTOTCOM", "NUMSEC", "CODTOT", "SIGOPE"};
	return columns;
}public static final String[] getColumnsTtotdat() {
	String columns[] = {"CODTOT", "NOMTOT"};
	return columns;
}public static final String[] getColumnsTusrorg() {
	String columns[] = {"CODORG","CODMOD", "CODUSR", "TXTNAM", "LSTNAM", "FAMNAM", "FLGSUP", "TXTPAS","TXTEML"};
	return columns;
}}