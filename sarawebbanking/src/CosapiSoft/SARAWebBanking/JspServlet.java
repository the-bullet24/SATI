package CosapiSoft.SARAWebBanking;

/**
 * This type was created in VisualAge.
 */
public final class JspServlet {
	
	public final static String MANAGER_HTML = "/sarawebbanking/SARAWebBanking/Manager.html";
	public final static String BUILDER_HTML = "/sarawebbanking/SARAWebBanking/Builder.html";
	public final static String ADMIN_HTML = "/sarawebbanking/SARAWebBanking/Admin.html";
	public final static String SARA_WEB_BANKING_HTML = "/sarawebbanking/sarawebbanking.html";
	public final static String ERROR_JSP = "/sarawebbanking/SARAWebBanking/Error.jsp"; //  target=" + (char) 34 + "_top" + (char) 34;
	public final static String ERROR_BUILDER_JSP = "/sarawebbanking/SARAWebBanking/PerfilBuilderError.jsp";
	public final static String ERROR_MANAGER_JSP = "/sarawebbanking/SARAWebBanking/PerfilManagerError.jsp";
	public final static String ERROR_GENERAL_JSP = "/sarawebbanking/SARAWebBanking/ErrorGeneral.jsp";
	// SARA Web Manager
	public final static String INICIO_SESION_JSP = "/sarawebbanking/SARAWebBanking/InicioSesion.jsp";
	public final static String INICIO_SESION_SERVLET = "/sarawebbanking/InicioSesionServlet";
	//
	
	public final static String COLOR_TIPO_PERSONA_JSP = "/sarawebbanking/SARAWebManager/Tablas/ColorTipoPersona.jsp";
	public final static String COLOR_TIPO_PERSONA_SERVLET = "/sarawebbanking/servlet/ColorTipoPersonaServlet";
	//
	public final static String MODIFICAR_CLAVE_JSP = "/sarawebbanking/SARAWebBanking/ModificarClave.jsp";
	public final static String MODIFICAR_CLAVE_SERVLET = "/sarawebbanking/servlet/ModificarClaveServlet";
	//
	public final static String REPORTE_ESTADISTICO_USO_JSP = "/sarawebbanking/SARAWebManager/ReporteEstadistico/ReporteEstadistico.jsp";
	public final static String REPORTE_ESTADISTICO_USO_SERVLET = "/sarawebbanking/servlet/ReporteEstadisticoServlet";
	//
	public final static String AFILIACION_AGENCIAS_JSP = "/sarawebbanking/SARAWebManager/ReporteEstadistico/AfiliacionAgencias.jsp";
	public final static String CONSOLIDADO_JSP = "/sarawebbanking/SARAWebManager/ReporteEstadistico/Consolidado.jsp";
	//
	public final static String GRUPO_PANTALLAS_JSP = "/sarawebbanking/SARAWebManager/MensajesdePantallas/GrupoMensajes.jsp";
	public final static String GRUPO_PANTALLAS_SERVLET = "/sarawebbanking/servlet/GrupoPantallasServlet";
	//
	public final static String MENSAJES_PANTALLAS_JSP = "/sarawebbanking/SARAWebManager/MensajesdePantallas/Mensajes.jsp";
	public final static String MENSAJES_PANTALLAS_SERVLET = "/sarawebbanking/servlet/MensajesPantallasServlet";
	//
	public final static String WAP_CCI_AHORROS_JSP = "/sarawebbanking/SARAWebManager/ReporteEstadistico/WAPCCIAho.jsp";
	public final static String WAP_CCI_CORRIENTE_JSP = "/sarawebbanking/SARAWebManager/ReporteEstadistico/WAPCCICte.jsp";
	
	public final static String WAP_CCI_AHORROS_SERVLET = "/sarawebbanking/servlet/WAPCCIAhoServlet";
	public final static String WAP_CCI_CORRIENTE_SERVLET = "/sarawebbanking/servlet/WAPCCICteServlet";
	
	
	public final static String WAP_CTA_AHORROS_JSP = "/sarawebbanking/SARAWebManager/ReporteEstadistico/WAPConsultasCtasAho.jsp";
	public final static String WAP_CTA_CORRIENTE_JSP_JSP = "/sarawebbanking/SARAWebManager/ReporteEstadistico/WAPConsultasCtasCte.jsp";
	public final static String WAP_TIPO_CAMBIO_JSP = "/sarawebbanking/SARAWebManager/ReporteEstadistico/WAPTipoCambio.jsp";
	public final static String WAP_CONSOLIDADO_JSP = "/sarawebbanking/SARAWebManager/ReporteEstadistico/WAPConsolidadaCtas.jsp";
	public final static String WAP_PRESTAMO_MULTIRED_JSP = "/sarawebbanking/SARAWebManager/ReporteEstadistico/WAPPrestamoMultired.jsp";
	
	public final static String REPORTE_PAGOS_JSP = "/sarawebbanking/SARAWebManager/ReporteEstadistico/PagoServicios.jsp";
	//
	public final static String LOG_DE_OPERACIONES_MANAGER_JSP = "/sarawebbanking/SARAWebBanking/LogDeOperaciones/LogDeOperacionesManager.jsp";
	public final static String LOG_DE_OPERACIONES_MANAGER_SERVLET = "/sarawebbanking/servlet/LogDeOperacionesManagerServlet";
	//
	public final static String DETALLE_LOG_DE_OPERACIONES_MANAGER_JSP = "/sarawebbanking/SARAWebBanking/LogDeOperaciones/DetalleLogManager.jsp";
	public final static String DETALLE_LOG_DE_OPERACIONES_MANAGER_SERVLET = "/sarawebbanking/servlet/DetalleLogManagerServlet";
	//
	public final static String DIARIO_ELECTRONICO_JSP = "/sarawebbanking/SARAWebManager/DiarioElectronico/DiarioElectronico.jsp";
	public final static String DIARIO_HISTORICO_JSP = "/sarawebbanking/SARAWebManager/DiarioElectronico/DiarioHistorico.jsp";
	public final static String DIARIO_ELECTRONICO_SERVLET = "/sarawebbanking/servlet/DiarioElectronicoServlet";
	//
	public final static String REFRENDO_JSP = "/sarawebbanking/SARAWebManager/DiarioElectronico/Refrendo.jsp";
	public final static String CONFIRMA_REFRENDO_JSP = "/sarawebbanking/SARAWebManager/ReporteEstadistico/EnviarRefrendo.jsp";
	public final static String REFRENDO_SERVLET = "/sarawebbanking/servlet/DiarioElectronicoServlet";
	//
	public final static String DETALLE_DIARIO_ELECTRONICO_JSP = "/sarawebbanking/SARAWebManager/DiarioElectronico/DetalleDiario.jsp";
	public final static String DETALLE_DIARIO_ELECTRONICO_SERVLET = "/sarawebbanking/servlet/DetalleDiarioServlet";
	//
	public final static String LOG_DE_OPERACIONES_BUILDER_JSP = "/sarawebbanking/SARAWebBanking/LogDeOperaciones/LogDeOperacionesBuilder.jsp";
	public final static String LOG_DE_OPERACIONES_BUILDER_SERVLET = "/sarawebbanking/servlet/LogDeOperacionesBuilderServlet";
	//
	public final static String DETALLE_LOG_DE_OPERACIONES_BUILDER_JSP = "/sarawebbanking/SARAWebBanking/LogDeOperaciones/DetalleLogBuilder.jsp";
	public final static String DETALLE_LOG_DE_OPERACIONES_BUILDER_SERVLET = "/sarawebbanking/servlet/DetalleLogBuilderServlet";
	//
	public final static String INFORMACION_DE_AGENCIAS_JSP = "/sarawebbanking/SARAWebManager/AdministracionDeAgencias/InformacionDeAgencias.jsp";
	public final static String INFORMACION_DE_AGENCIAS_SERVLET = "/sarawebbanking/servlet/InformacionDeAgenciasServlet";
	public final static String BLOQUEO_SUCURSAL_JSP = "/sarawebbanking/SARAWebManager/AdministracionDeAgencias/BloqueoDeOficina.jsp";
	//
	public final static String ADMINISTRACION_DE_AGENCIAS_JSP = "/sarawebbanking/SARAWebManager/AdministracionDeAgencias/AdmAg.jsp";
	public final static String ADMINISTRACION_DE_AGENCIAS_SERVLET = "/sarawebbanking/servlet/AdministracionDeAgenciasServlet";
	//
	public final static String DEPARTAMENTO_JSP = "/sarawebbanking/SARAWebManager/Tablas/Departamentos.jsp";
	public final static String DEPARTAMENTO_SERVLET = "/sarawebbanking/servlet/DepartamentoServlet";
	//
	public final static String OFICINA_JSP = "/sarawebbanking/SARAWebManager/Tablas/Oficinas.jsp";
	public final static String OFICINA_SERVLET = "/sarawebbanking/servlet/OficinaServlet";
	//
	public final static String AFILIACION_SERVICIOS_JSP = "/sarawebbanking/SARAWebManager/ReporteEstadistico/AfiliacionServicios.jsp";
	public final static String AFILIACION_CLIENTES_JSP = "/sarawebbanking/SARAWebManager/ReporteEstadistico/AfiliacionClientes.jsp";
	public final static String AFILIACIONES_SERVLET = "/sarawebbanking/servlet/AfiliacionesServlet";
	//
	public final static String LIMITES_JSP = "/sarawebbanking/SARAWebManager/Tablas/Limites.jsp";
	public final static String LIMITES_SERVLET = "/sarawebbanking/servlet/LimitesServlet";
	//
	public final static String HORTRA_JSP = "/sarawebbanking/SARAWebManager/Tablas/Trahor.jsp";
	public final static String HORTRA_SERVLET = "/sarawebbanking/servlet/TrahorServlet";
	//
	public final static String MSGHST_JSP = "/sarawebbanking/SARAWebManager/Tablas/MsgHst.jsp";
	public final static String MSGHST_SERVLET = "/sarawebbanking/servlet/MsgHstServlet";
	//
	public final static String MENU_JSP = "/sarawebbanking/SARAWebManager/Tablas/Menu.jsp";
	public final static String MENU_SERVLET = "/sarawebbanking/servlet/MenuServlet";
	//
	public final static String LIMITES_DETALLE_JSP = "/sarawebbanking/SARAWebManager/Tablas/LimitesDetalle.jsp";
	public final static String LIMITES_DETALLE_SERVLET = "/sarawebbanking/servlet/LimitesDetalleServlet";
	//
	public final static String MENSAJES_DE_COMUNICACION_JSP = "/sarawebbanking/SARAWebManager/Tablas/MensajesDeComunicacion.jsp";
	public final static String MENSAJES_DE_COMUNICACION_SERVLET = "/sarawebbanking/servlet/MensajesDeComunicacionServlet";
	//
	public final static String CANALES_DE_ATENCION_JSP = "/sarawebbanking/SARAWebManager/Tablas/CanalesDeAtencion.jsp";
	public final static String CANALES_DE_ATENCION_SERVLET = "/sarawebbanking/servlet/CanalesDeAtencionServlet";
	//
	public final static String AYUDAS_DE_CAMPO_JSP = "/sarawebbanking/SARAWebManager/AyudaDeCampo/AyudaCampo.jsp";
	public final static String AYUDAS_DE_CAMPO_SERVLET = "/sarawebbanking/servlet/AyudaDeCampoServlet";
	//
	public final static String PRODUCTOS_CTE_JSP = "/sarawebbanking/SARAWebManager/ProductosAhorros/ProductosCte.jsp";
	public final static String PRODUCTOS_CTE_SERVLET = "/sarawebbanking/servlet/ProductosCteServlet";
	//
	public final static String PRODUCTOS_CTE_CONF_JSP = "/sarawebbanking/SARAWebManager/ProductosAhorros/ProductosCteConf.jsp";
	public final static String PRODUCTOS_CTE_CONF_SERVLET = "/sarawebbanking/servlet/ProductosCteConfServlet";
	//
	public final static String PRODUCTOS_AHORROS_JSP = "/sarawebbanking/SARAWebManager/ProductosAhorros/ProductosAhorros.jsp";
	public final static String PRODUCTOS_AHORROS_SERVLET = "/sarawebbanking/servlet/ProductosAhorrosServlet";
	//
	public final static String PRODUCTOS_AHORROS_CONF_JSP = "/sarawebbanking/SARAWebManager/ProductosAhorros/ProductosAhorrosConf.jsp";
	public final static String PRODUCTOS_AHORROS_CONF_SERVLET = "/sarawebbanking/servlet/ProductosAhorrosConfServlet";
	//
	public final static String PRODUCTOS_CRED_JSP = "/sarawebbanking/SARAWebManager/ProductosAhorros/ProductosCred.jsp";
	public final static String PRODUCTOS_CRED_SERVLET = "/sarawebbanking/servlet/ProductosCredServlet";
	//
	public final static String PRODUCTOS_CRED_CONF_JSP = "/sarawebbanking/SARAWebManager/ProductosAhorros/ProductosCredConf.jsp";
	public final static String PRODUCTOS_CRED_CONF_SERVLET = "/sarawebbanking/servlet/ProductosCredConfServlet";
	//
	public final static String ELEMENTOS_DE_AYUDA_JSP = "/sarawebbanking/SARAWebManager/AyudaDeCampo/ItemsAyuda.jsp";
	public final static String ELEMENTOS_DE_AYUDA_SERVLET = "/sarawebbanking/servlet/ItemsDeAyudaServlet";
	//
	public final static String GRUPO_DE_MENSAJES_JSP = "/sarawebbanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp";
	public final static String GRUPO_DE_MENSAJES_SERVLET = "/sarawebbanking/servlet/GrupoDeMensajesServlet";
	//
	
	public final static String BANNER_PROMOCIONAL_JSP = "/sarawebbanking/SARAWebManager/Tablas/BannerPromocional.jsp";
	public final static String BANNER_PROMOCIONAL_SERVLET = "/sarawebbanking/servlet/BannerPromocionalServlet";
	//
	public final static String MENSAJES_DEL_APLICATIVO_JSP = "/sarawebbanking/SARAWebManager/MensajesAplicativo/DatosMensajes.jsp";
	public final static String MENSAJES_DEL_APLICATIVO_SERVLET = "/sarawebbanking/servlet/DatosDeMensajesServlet";
	//
	public final static String GESTION_DE_ACTIVIDADES_JSP = "/sarawebbanking/SARAWebManager/GestionDeActividades/GestionDeActividades.jsp";
	public final static String GESTION_DE_ACTIVIDADES_SERVLET = "/sarawebbanking/servlet/GestionDeActividadesServlet";
	//
	public final static String GESTION_DE_PERFILES_JSP = "/sarawebbanking/SARAWebManager/GestionDePerfiles/GestionDePerfiles.jsp";
	public final static String GESTION_DE_PERFILES_SERVLET = "/sarawebbanking/servlet/GestionDePerfilesServlet";
	//
	public final static String ACTIVIDADES_DE_PERFILES_JSP = "/sarawebbanking/SARAWebManager/GestionDePerfiles/ActividadesDePerfiles.jsp";
	public final static String ACTIVIDADES_DE_PERFILES_SERVLET = "/sarawebbanking/servlet/ActividadesDePerfilesServlet";
	//
	public final static String PERFILES_TELLER_JSP = "/sarawebbanking/SARAWebManager/GestionDePerfiles/PerfilesTeller.jsp";
	public final static String PERFILES_TELLER_SERVLET = "/sarawebbanking/servlet/PerfilesTellerServlet";
	//
	public final static String GRUPOS_TRXS_PERFILES_JSP = "/sarawebbanking/SARAWebManager/GestionDePerfiles/GruposTrxPerfiles.jsp";
	public final static String GRUPOS_TRXS_PERFILES_SERVLET = "/sarawebbanking/servlet/GruposTrxPerfilesServlet";
	//
	public final static String ADD_RES_TRXS_JSP = "/sarawebbanking/SARAWebManager/GestionDePerfiles/AddRestTrxs.jsp";
	public final static String ADD_RES_TRXS_SERVLET = "/sarawebbanking/servlet/AddRestTrxsServlet";
	//
	public final static String GESTION_DE_USUARIOS_JSP = "/sarawebbanking/SARAWebManager/GestionDeUsuarios/GestionDeUsuarios.jsp";
	public final static String GESTION_DE_USUARIOS_SERVLET = "/sarawebbanking/servlet/GestionDeUsuariosServlet";
	//
	public final static String PERFILES_DE_USUARIOS_JSP = "/sarawebbanking/SARAWebManager/GestionDeUsuarios/PerfilesDeUsuarios.jsp";
	public final static String PERFILES_DE_USUARIOS_SERVLET = "/sarawebbanking/servlet/PerfilesDeUsuariosServlet";
	//
	public final static String LOG_DE_USUARIOS_BRANCH_JSP = "/sarawebbanking/SARAWebManager/GestionDeUsuarios/LogDeUsuariosBranch.jsp";
	public final static String LOG_DE_USUARIOS_BRANCH_SERVLET = "/sarawebbanking/servlet/LogDeUsuariosBranchServlet";
	//
	// Creditos
	//
	public final static String TPRGDAT_JSP = "/sarawebbanking/SARAWebManager/Creditos/JSPTprgdat.jsp";
	public final static String BEAN_TPRGDAT_SERVLET = "/sarawebbanking/servlet/BeanTprgdatServlet";
	//
	public final static String TLINDAT_JSP = "/sarawebbanking/SARAWebManager/Creditos/JSPTlindat.jsp";
	public final static String BEAN_TLINDAT_SERVLET = "/sarawebbanking/servlet/BeanTlindatServlet";
	//
	public final static String TPATDAT_JSP = "/sarawebbanking/SARAWebManager/Creditos/JSPTpatdat.jsp";
	public final static String BEAN_TPATDAT_SERVLET = "/sarawebbanking/servlet/BeanTpatdatServlet";
	//
	public final static String TNODPAT_JSP = "/sarawebbanking/SARAWebManager/Creditos/JSPTnodpat.jsp";
	public final static String BEAN_TNODPAT_SERVLET = "/sarawebbanking/servlet/BeanTnodpatServlet";
	//
	public final static String TNODPAT01_JSP = "/sarawebbanking/SARAWebManager/Creditos/JSPTnodpat01.jsp";
	public final static String BEAN_TNODPAT01_SERVLET = "/sarawebbanking/servlet/BeanTnodpat01Servlet";
	//
	public final static String TNODPAT02_JSP = "/sarawebbanking/SARAWebManager/Creditos/JSPTnodpat02.jsp";
	public final static String BEAN_TNODPAT02_SERVLET = "/sarawebbanking/servlet/BeanTnodpat02Servlet";
	//
	public final static String TNODDAT_JSP = "/sarawebbanking/SARAWebManager/Creditos/JSPTnoddat.jsp";
	public final static String BEAN_TNODDAT_SERVLET = "/sarawebbanking/servlet/BeanTnoddatServlet";
	//
	public final static String TCREDAT_JSP = "/sarawebbanking/SARAWebManager/Creditos/JSPTcredat.jsp";
	public final static String BEAN_TCREDAT_SERVLET = "/sarawebbanking/servlet/BeanTcredatServlet";
	//
	public final static String TTASDAT_JSP = "/sarawebbanking/SARAWebManager/Creditos/JSPTtasdat.jsp";
	public final static String BEAN_TTASDAT_SERVLET = "/sarawebbanking/servlet/BeanTtasdatServlet";
	//
	public final static String TNODTAS_JSP = "/sarawebbanking/SARAWebManager/Creditos/JSPTnodtas.jsp";
	public final static String BEAN_TNODTAS_SERVLET = "/sarawebbanking/servlet/BeanTnodtasServlet";
	//
	public final static String TTASNOD_JSP = "/sarawebbanking/SARAWebManager/Creditos/JSPTtasnod.jsp";
	public final static String BEAN_TTASNOD_SERVLET = "/sarawebbanking/servlet/BeanTtasnodServlet";
	//
	public final static String TUSRNOD_JSP = "/sarawebbanking/SARAWebManager/Creditos/JSPTusrnod.jsp";
	public final static String BEAN_TUSRNOD_SERVLET = "/sarawebbanking/servlet/BeanTusrnodServlet";
	//
	//SARA Web Builder
	public final static String IMPORTACION_JSP = "/sarawebbanking/SARAWebBuilder/Distribucion/Importacion.jsp";
	public final static String IMPORTACION_SERVLET = "/sarawebbanking/servlet/ImportTablesServlet";
	//
	public final static String DISTRIBUCION_JSP = "/sarawebbanking/SARAWebBuilder/Distribucion/Distribucion.jsp";
	public final static String DISTRIBUCION_SERVLET = "/sarawebbanking/servlet/ExportTablesServlet";
	//
	public final static String CLASES_JSP = "/sarawebbanking/SARAWebBuilder/Transacciones/Clases.jsp";
	public final static String CLASES_SERVLET = "/sarawebbanking/servlet/ClasesServlet";
	//
	public final static String METODOS_JSP = "/sarawebbanking/SARAWebBuilder/Transacciones/Metodos.jsp";
	public final static String METODOS_SERVLET = "/sarawebbanking/servlet/MetodosServlet";
	//
	public final static String PROPERTIES_JSP = "/sarawebbanking/SARAWebBuilder/Transacciones/Properties.jsp";
	public final static String PROPERTIES_SERVLET = "/sarawebbanking/servlet/PropertiesServlet";
	//
	public final static String DICCIONARIO_JSP = "/sarawebbanking/SARAWebBuilder/Diccionario/Diccionario.jsp";
	public final static String DICCIONARIO_SERVLET = "/sarawebbanking/servlet/DiccionarioServlet";
	//
	public final static String GRUPO_TRANSACCIONES_JSP = "/sarawebbanking/SARAWebBuilder/Transacciones/GrupoDeTransacciones.jsp";
	public final static String GRUPO_TRANSACCIONES_SERVLET = "/sarawebbanking/servlet/GrupoDeTransaccionesServlet";
	//
	public final static String TRANSACCIONES_JSP = "/sarawebbanking/SARAWebBuilder/Transacciones/Transacciones.jsp";
	public final static String TRANSACCIONES_SERVLET = "/sarawebbanking/servlet/TransaccionesServlet";
	//
	public final static String DATOS_TRANSACCIONES_JSP = "/sarawebbanking/SARAWebBuilder/Transacciones/DatosDeTransaccion.jsp";
	public final static String DATOS_TRANSACCIONES_SERVLET = "/sarawebbanking/servlet/DatosDeTransaccionesServlet";
	//
	public final static String MASK_TRANSACCIONES_JSP = "/sarawebbanking/SARAWebBuilder/Transacciones/Mascara.jsp";
	public final static String MASK_TRANSACCIONES_SERVLET = "/sarawebbanking/servlet/MascaraServlet";
	//
	public final static String PROPERTIES_TRANSACCIONES_JSP = "/sarawebbanking/SARAWebBuilder/masc/properties.jsp";
	//	public final static String PROPERTIES_TRANSACCIONES_SERVLET = "/sarawebbanking/servlet/Mascara";
	//
	public final static String JOURNAL_TRANSACCIONES_JSP = "/sarawebbanking/SARAWebBuilder/Transacciones/JournalTransaccion.jsp";
	public final static String JOURNAL_TRANSACCIONES_SERVLET = "/sarawebbanking/servlet/JournalTransaccionesServlet";
	//
	public final static String FASE_TRANSACCIONES_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/Fase.jsp";
	public final static String FASE_TRANSACCIONES_SERVLET = "/sarawebbanking/servlet/FaseServlet";
	//
	public final static String ESQUEMA_TRANSACCIONES_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/Esquema.jsp";
	public final static String ESQUEMA_TRANSACCIONES_SERVLET = "/sarawebbanking/servlet/EsquemaServlet";
	//
	public final static String CONSULTA_DICCIONARIO_JSP = "/sarawebbanking/SARAWebBuilder/Transacciones/ConsultaDiccionario.jsp";
	public final static String CONSULTA_DICCIONARIO_SERVLET = "/sarawebbanking/servlet/ConsultaDiccionarioServlet";
	//
	public final static String CONSULTA_ESQUEMA_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/ConsultaEsquema.jsp";
	public final static String CONSULTA_ESQUEMA_SERVLET = "/sarawebbanking/servlet/ConsultaEsquemaServlet";
	//
	public final static String CONSULTA_FASE_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/ConsultaFase.jsp";
	public final static String CONSULTA_FASE_SERVLET = "/sarawebbanking/servlet/ConsultaFaseServlet";
	//
	public final static String TMETARGALT_JSP = "/sarawebbanking/SARAWebBuilder/Transacciones/Tmetargalt.jsp";
	public final static String TMETARGALT_SERVLET = "/sarawebbanking/servlet/TmetargaltServlet";
	//
	public final static String TMETARG_JSP = "/sarawebbanking/SARAWebBuilder/Transacciones/Tmetarg.jsp";
	public final static String TMETARG_SERVLET = "/sarawebbanking/servlet/TmetargServlet";
	//
	public final static String FASE_TRX_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/FaseTrx.jsp";
	public final static String FASE_TRX_SERVLET = "/sarawebbanking/servlet/FaseTrxServlet";
	//
	public final static String FASE_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/Fase.jsp";
	public final static String FASE_SERVLET = "/sarawebbanking/servlet/FaseServlet";
	//
	public final static String FASE_TRX0_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/Fase0.jsp";
	public final static String FASE_TRX0_SERVLET = "/sarawebbanking/servlet/FaseTrx0Servlet";
	//
	public final static String FASE_TRX1_1_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/Fase1_1.jsp";
	public final static String FASE_TRX1_1_SERVLET = "/sarawebbanking/servlet/FaseTrx1_1Servlet";
	//
	public final static String FASE_TRX1_2_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/Fase1_2.jsp";
	public final static String FASE_TRX1_2_SERVLET = "/sarawebbanking/servlet/FaseTrx1_2Servlet";
	//
	public final static String FASE_TRX1_3_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/Fase1_3.jsp";
	public final static String FASE_TRX1_3_SERVLET = "/sarawebbanking/servlet/FaseTrx1_3Servlet";
	//
	public final static String FASE_TRX2_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/Fase2.jsp";
	public final static String FASE_TRX2_SERVLET = "/sarawebbanking/servlet/FaseTrx2Servlet";
	//
	public final static String FASE_TRX3_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/Fase3.jsp";
	public final static String FASE_TRX3_SERVLET = "/sarawebbanking/servlet/FaseTrx3Servlet";
	//
	public final static String FASE_TRX4_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/Fase4.jsp";
	public final static String FASE_TRX4_SERVLET = "/sarawebbanking/servlet/FaseTrx4Servlet";
	//
	public final static String ESQUEMA_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/Esquema.jsp";
	public final static String ESQUEMA_SERVLET = "/sarawebbanking/servlet/EsquemaServlet";
	//
	public final static String ESQUEMA_TRX1_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/Esquema1.jsp";
	public final static String ESQUEMA_TRX1_SERVLET = "/sarawebbanking/servlet/EsquemaTrx1Servlet";
	//
	public final static String ESQUEMA_TRX2_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/Esquema2.jsp";
	public final static String ESQUEMA_TRX2_SERVLET = "/sarawebbanking/servlet/EsquemaTrx2Servlet";
	//
	public final static String ESQUEMA_TRX3_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/Esquema3.jsp";
	public final static String ESQUEMA_TRX3_SERVLET = "/sarawebbanking/servlet/EsquemaTrx3Servlet";
	//
	public final static String ESQUEMA_TRX4_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/Esquema4.jsp";
	public final static String ESQUEMA_TRX4_SERVLET = "/sarawebbanking/servlet/EsquemaTrx4Servlet";
	//
	//
	public final static String FASE_ALT_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/FaseAlt.jsp";
	public final static String FASE_ALT_SERVLET = "/sarawebbanking/servlet/FaseAltServlet";
	//
	public final static String FASE_CLA_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/FaseCla.jsp";
	public final static String FASE_CLA_SERVLET = "/sarawebbanking/servlet/FaseClaServlet";
	//
	public final static String FASE_MET_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/FaseMet.jsp";
	public final static String FASE_MET_SERVLET = "/sarawebbanking/servlet/FaseMetServlet";
	//
	public final static String FASE_ARG_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/FaseArg.jsp";
	public final static String FASE_ARG_SERVLET = "/sarawebbanking/servlet/FaseArgServlet";
	//
	public final static String FASE_SEQ_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/FaseSeq.jsp";
	public final static String FASE_SEQ_SERVLET = "/sarawebbanking/servlet/FaseSeqServlet";
	//
	public final static String ESQ_CLA_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/EsqCla.jsp";
	public final static String ESQ_CLA_SERVLET = "/sarawebbanking/servlet/EsqClaServlet";
	//
	public final static String ESQ_MET_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/EsqMet.jsp";
	public final static String ESQ_MET_SERVLET = "/sarawebbanking/servlet/EsqMetServlet";
	//
	public final static String ESQ_ARG_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/EsqArg.jsp";
	public final static String ESQ_ARG_SERVLET = "/sarawebbanking/servlet/EsqArgServlet";
	//
	public final static String ESQ_SEQ_JSP = "/sarawebbanking/SARAWebBuilder/Esquema/EsqSeq.jsp";
	public final static String ESQ_SEQ_SERVLET = "/sarawebbanking/servlet/EsqSeqServlet";
	//
	//
	public final static String FASE_DICC_JSP = "/sarawebbanking/SARAWebBuilder/masc/Esquema/FaseDicc.jsp";
	public final static String FASE_DICC_SERVLET = "/sarawebbanking/servlet/FaseDiccServlet";
	//
	public final static String FASE_DICC0_JSP = "/sarawebbanking/SARAWebBuilder/masc/Esquema/FaseDicc0.jsp";
	public final static String FASE_DICC0_SERVLET = "/sarawebbanking/servlet/FaseDicc0Servlet";
	//
	public final static String FASE_DICC1_JSP = "/sarawebbanking/SARAWebBuilder/masc/Esquema/FaseDicc1.jsp";
	public final static String FASE_DICC1_SERVLET = "/sarawebbanking/servlet/FaseDicc1Servlet";
	//
	public final static String FASE_DICC2_JSP = "/sarawebbanking/SARAWebBuilder/masc/Esquema/FaseDicc2.jsp";
	public final static String FASE_DICC2_SERVLET = "/sarawebbanking/servlet/FaseDicc2Servlet";
	//
	public final static String FASE_DICC3_JSP = "/sarawebbanking/SARAWebBuilder/masc/Esquema/FaseDicc3.jsp";
	public final static String FASE_DICC3_SERVLET = "/sarawebbanking/servlet/FaseDicc3Servlet";
	//
	public final static String FASE_DICC4_JSP = "/sarawebbanking/SARAWebBuilder/masc/Esquema/FaseDicc4.jsp";
	public final static String FASE_DICC4_SERVLET = "/sarawebbanking/servlet/FaseDicc4Servlet";
	//
	public final static String FASE_DICC5_JSP = "/sarawebbanking/SARAWebBuilder/masc/Esquema/FaseDicc5.jsp";
	public final static String FASE_DICC5_SERVLET = "/sarawebbanking/servlet/FaseDicc5Servlet";
	//
	public final static String ESQUEMA_DICC_JSP = "/sarawebbanking/SARAWebBuilder/masc/Esquema/EsquemaDicc.jsp";
	public final static String ESQUEMA_DICC_SERVLET = "/sarawebbanking/servlet/EsquemaDiccServlet";
	//
	public final static String ESQUEMA_DICC1_JSP = "/sarawebbanking/SARAWebBuilder/masc/Esquema/EsquemaDicc1.jsp";
	public final static String ESQUEMA_DICC1_SERVLET = "/sarawebbanking/servlet/EsquemaDicc1Servlet";
	//
	public final static String ESQUEMA_DICC2_JSP = "/sarawebbanking/SARAWebBuilder/masc/Esquema/EsquemaDicc2.jsp";
	public final static String ESQUEMA_DICC2_SERVLET = "/sarawebbanking/servlet/EsquemaDicc2Servlet";
	//
	public final static String ESQUEMA_DICC3_JSP = "/sarawebbanking/SARAWebBuilder/masc/Esquema/EsquemaDicc3.jsp";
	public final static String ESQUEMA_DICC3_SERVLET = "/sarawebbanking/servlet/EsquemaDicc3Servlet";
	//
	public final static String ESQUEMA_DICC4_JSP = "/sarawebbanking/SARAWebBuilder/masc/Esquema/EsquemaDicc4.jsp";
	public final static String ESQUEMA_DICC4_SERVLET = "/sarawebbanking/servlet/EsquemaDicc4Servlet";
	//
	// INICIO DE DIA
	public final static String INICIO_DE_DIA1_JSP = "/SARAWebBanking/InicioDeDia/InicioDeDia1.jsp";
	public final static String INICIO_DE_DIA1_SERVLET = "/sarawebbanking/servlet/InicioDeDia1Servlet";
	//
	public final static String INICIO_DE_DIA2_JSP = "/SARAWebBanking/InicioDeDia/InicioDeDia2.jsp";
	public final static String INICIO_DE_DIA2_SERVLET = "/sarawebbanking/servlet/InicioDeDia2Servlet";
	//
	public final static String INICIO_DE_DIA3_JSP = "/SARAWebBanking/InicioDeDia/InicioDeDia3.jsp";
	public final static String INICIO_DE_DIA3_SERVLET = "/sarawebbanking/servlet/InicioDeDia3Servlet";
	//
	public final static String INICIO_DE_DIA4_JSP = "/SARAWebBanking/InicioDeDia/InicioDeDia4.jsp";
	public final static String INICIO_DE_DIA4_SERVLET = "/sarawebbanking/servlet/InicioDeDia4Servlet";
	//
	// SARA Web Teller
	public final static String TELLER_HTML = "/SARAWebTeller/SARAWebTeller/SARAWebTeller.html";
	public final static String SARA_WEB_TELLER_HTML = "/SARAWebTeller/SARAWebTeller.html";
	public final static String ERROR_TELLER_JSP = "/SARAWebTeller/SARAWebTeller/Error.jsp";
	public final static String END_SESION_TELLER_JSP = "/SARAWebTeller/SARAWebTeller/EndSesion.jsp";
	//
	//public final static String DIARIO_ELECTRONICO_JSP = "/SARAWebTeller/DiarioElectronico/DiarioElectronico.jsp";
	//public final static String DIARIO_ELECTRONICO_SERVLET = "/sarawebbanking/servlet/CosapiSoft.SARAWebTeller.DiarioElectronicoServlet";
	//
	public final static String LOGIN_JSP = "/SARAWebTeller/SARAWebTeller/Login.jsp";
	public final static String LOGIN_SERVLET = "/sarawebbanking/servlet/CosapiSoft.SARAWebTeller.LoginServlet";
	//
/**
 * JspServlet constructor comment.
 */
public JspServlet() {
	super();
}
	//
	public final static String CAJAS_JSP = "/sarawebbanking/SARAWebBuilder/Transacciones/Caja.jsp";	public final static String CAJAS_SERVLET = "/sarawebbanking/servlet/CajaServlet";	//
	public final static String EMPRESA_JSP = "/sarawebbanking/SARAWebManager/GestionDeEmpresas/JSPTorgdat.jsp";	public final static String EMPRESA_SERVLET = "/sarawebbanking/servlet/EmpresaServlet";	//
	public final static String FACULTAD_CUENTA_JSP = "/sarawebbanking/SARAWebManager/GestionDeEmpresas/JSPTfacacc.jsp";	public final static String FACULTAD_CUENTA_SERVLET = "/sarawebbanking/servlet/FacultadCuentaServlet";	public final static String NOMINA__SERVLET = "/sarawebbanking/servlet/NominaServlet";	public final static String NOMINA_AUTORIZACION_JSP = "/sarawebbanking/SARAWebManager/GestionDeEmpresas/JSPTlstaut.jsp";	public final static String NOMINA_AUTORIZACION_SERVLET = "/sarawebbanking/servlet/NominaAutorizacionServlet";	//
	public final static String NOMINA_DETALLE_JSP = "/sarawebbanking/SARAWebManager/GestionDeEmpresas/JSPTlstdet.jsp";	public final static String NOMINA_DETALLE_SERVLET = "/sarawebbanking/servlet/NominaDetalleServlet";	//
	public final static String NOMINA_JSP = "/sarawebbanking/SARAWebManager/GestionDeEmpresas/JSPTorglst.jsp";	//
	public final static String NOTIFICACIONES_JSP = "/sarawebbanking/SARAWebManager/GestionDeEmpresas/JSPTorgnot.jsp";	public final static String NOTIFICACIONES_SERVLET = "/sarawebbanking/servlet/NotificacionServlet";	//
	public final static String TOTAL_JSP = "/sarawebbanking/SARAWebBuilder/Transacciones/Total.jsp";	public final static String TOTAL_SERVLET = "/sarawebbanking/servlet/TotalServlet";	//
	public final static String TOTALCOM_JSP = "/sarawebbanking/SARAWebBuilder/Transacciones/TotalCom.jsp";	public final static String TOTALCOM_SERVLET = "/sarawebbanking/servlet/TotalComServlet";	//
	public final static String TOTALCOMDET_JSP = "/sarawebbanking/SARAWebBuilder/Transacciones/TotalComDet.jsp";	public final static String TOTALCOMDET_SERVLET = "/sarawebbanking/servlet/DetalleTotalComServlet";	public final static String USUARIOS_EMPRESA__SERVLET = "/sarawebbanking/servlet/UsuariosEmpresaServlet";	//
	public final static String USUARIOS_EMPRESA_JSP = "/sarawebbanking/SARAWebManager/GestionDeEmpresas/JSPTusrorg.jsp";}