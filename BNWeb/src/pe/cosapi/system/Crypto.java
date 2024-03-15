/*
 * Created on 30/01/2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pe.cosapi.system;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Hashtable;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import pe.bn.telegiro.action.TelegiroAction;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ObjectUtil;

public class Crypto {
	private static LoggerSati log3 = LoggerSati.getInstance(Crypto.class.getName());
	
	private static String encoding = "CP037";
    public static SecretKeyFactory skfDesPad;
    public static SecretKeyFactory skfDesNoPad;
    public static SecretKeyFactory skfCifPad;
    public static SecretKeyFactory skfCifNoPad;
    public static String sClaveParaClaves;
    
    public Crypto() {
    }
    
public static void main ( String[] args ) throws UnsupportedEncodingException{
//FFFFF5FFFFF5FFFFF50CCC01F000000000000000000000000000000000000000000000       
//11111F22222F33333FE111F2F000000000000000000000000000000000000000000000 
//texto = "f1f1f1f1f15ff2f2f2f2f25ff3f3f3f3f35f0ec1c1c10f12ff";
//texto = "c4c5d4d6e2e3d9c1c3c9d6d540c4c540c5d5c3d9c9d7e3c1d4c9c5d5e3d6";

//String ret = "9d37450b7db9903329d0fca503c8e4722c96fbdcd1ba4805df3addd8b87d";//encripta(texto,claveA);
//ret = "3decae4b951cd824c49858c34348fa713c49190d5266fb56ed";

//String rev = desencripta(ret,claveA);
//log.warn("mi cadena final en hexa es : "+rev);
//texto = "f0f0f0f0f0f0f0f0f0f0f1f1f1f1f1f0f0f0f0f0f0f0f0f0f0f3f3f3f3f3c1c1c1404040404040404040404040404040404040404040404040404040f0f6f6f1";
//texto = "f0f0f0f0f0f0f0f0f0f0f6f6f6f6f6f0f0f0f0f0f0f0f0f7f7f6f7f6f7f6c1d3c6c1d6d4c5c7c1404040404040404040404040404040404040404040f0f6f6f1";
//String cad = getMac(texto,claveMac);
//log.warn("mi cadena final en hexa es : "+cad);   

//String nom = "alex valencia";
//String hexnom = ToHexa(nom.getBytes());
//String hexnom = "f1f1f1f1f15ff2f2f2f2f25ff3f3f3f3f35f0ec1c1c10f12ff";
//log.warn("cadena original en hexa es : "+hexnom);          
/*
String cad = encripta3DESede2Key(hexnom,"9d37450b7db99033","4805df3addd8b87d");
log.warn("cadena encriptada con 3des es  : "+cad);   
cad = desencripta3DESede2Key(cad,"0821ADB21B0DE766","BEB089C946C1824E");
log.warn("cadena desencriptada con 3des es  : "+cad);   
cad = encriptaDES(hexnom,"9d37450b7db99033");
log.warn("cadena encriptada con des es  : "+cad);   
cad = desencriptaDES(cad,"9d37450b7db99033");
log.warn("cadena desencriptada con 3des es  : "+cad);   
cad = getMac(nom,claveMac);
log.warn("mi mac final en hexa es : "+cad);   
*/
/*
String cad = encripta3DESede2Key(hexnom,"0821ADB21B0DE766","BEB089C946C1824E");
log.warn("cadena encriptada con 3des es  : "+cad);   
cad = desencripta3DESede2Key(cad,"0821ADB21B0DE766","BEB089C946C1824E");
log.warn("cadena desencriptada con 3des es  : "+cad);   
cad = encriptaDES(hexnom,"0821ADB21B0DE766");
log.warn("cadena encriptada con des es  : "+cad);   
cad = desencriptaDES(cad,"0821ADB21B0DE766");
log.warn("cadena desencriptada con 3des es  : "+cad);   
*/
String claveDES_A = "cb778cc36f339f2b";
//String claveDES_B = "BEB089C946C1824E";
/*
String textoDES = "11111" + (char)172 + "22222" + (char)172 + "33333" + (char)172 + (char)14 + "AAA" + (char)15 + (char)18 + (char)255;
byte textoDESEnEbcdic[] = AsciiToEbcdic(new String(textoDES));
String textoDESEnHexa = Crypto.ToHexa(textoDESEnEbcdic);
String cadDES = Crypto.encriptaDES(textoDESEnHexa, claveDES_A);
log.warn("cadena encriptada con des es : " + cadDES);   
String cad1 = Crypto.desencriptaDES(cadDES, claveDES_A);
log.warn("cadena desencriptada con des es : " + cad1);   
String textoDESEnAscii = EbcdicToAscii(Crypto.ToAscii2(cad1));
log.warn("cadena desencriptada con 3des en Ascii es : " + textoDESEnAscii);
*/

char cDelimDatoNumerico = 95;
char cFinTrama = 255;

StringBuffer lbody = new StringBuffer();
lbody.append(new String(AsciiToEbcdic("0001")));
lbody.append(cDelimDatoNumerico);
lbody.append(cFinTrama);

//String sTextoPlano = lbody.toString().getBytes();
//byte bTextoPlanoEnEbcdic[] = AsciiToEbcdic(lbody.toString().getBytes());
String sTextoPlanoEnHexa = Crypto.ToHexa(lbody.toString().getBytes());
sTextoPlanoEnHexa = "220db16628cb98c39f0f79db2a11c4d132b11c7f5ceee5015f2cda9809d242a8af403f2e4e7d34a3d7c93f5952592895ec64e6161d3fdf7142429c8ee68acc65c4da5840fe7d6ebcb3ae7e1b3e65c2de748623f410b2ffe8aec0cfe7b270d80ce0b8b73a7df916de0bfcddd06610644c053fd696895026276396b717431ad54bcecd7acb5bd7211aaf795815f4e1916bf9963d93985f10b6421b359f7f5b94fbe23f4bf5a7692926eebf1873eaa61416352e633464e6410438f8922d4eee87593225706322cde910ebba74132946625d2fcce5204d70c745f3b6060b7a5f4760add05f58e9632d766b6b587aa56d5ad9f150bf6a06ab119915fc29af2aa97eb78a6f96d72870223ac88b9bffca54b30f351752f28edef9479be5767e482615e16f61e637834014924f0e264c35f8e4362bb1d31445435d3df5ac9482fbc317af2f65a51057faa4e33316feea2ecdda5438fb5ea3a4d73b725741fb7414c875ddd4d7d59e0f737078f4b0b828f5e647b1679cef6878060121fb7c2f237b0edcc84b3fd5bde4c483340d212be7fe29080a16ec82675cd1e62c5021d4df297fad0208fd26373040267873c6cc366b54826ce44e303626bd1e5fc7e898620eda598727455c4e1b3bf83c1194bc86cb6597147fccc389283fb350f2333286b93baa5cce177055277d56947768d50df93fbf58a587f53a98f2d15e5c10fec0726516de55224c4ed012240983a2276e643c3a5c84227b26c88b918243d8420afb5fb24ef9f72e199cd5a0dd0aa8d6bf794bf20aabc7714f6b1a034a3fffbf43d37a624f62284b0a25ec4de627ed784e5fbdd3c926a329d4b844b3ea079918791e30c891ae02353f0dc5639b1a9bd9f1e05c193fb296c432e315aba1946f46b50554b040e126a4afee31f1077c12fbfb786794cf05ebf0dddc5c167b5356dcc893fa10d1a1944880464d53aecebef4cf50dd2885e2f51304bbaad37886613e8864b4d4f33f2677435aed775b078c4d0eaa83ceba1d861fec27a391c0fd69d0dd1c26aa61c57e01723f72e3d27b953fcc0969c6e1198c7729c8b10da0fca7ed13a6ae6d662fa2f6cffb28392e9ab50a0ec356a8d9ee2684974b283fb79549156ad42da4123ec58bdd5b03bf7eb7dc37b42e1acc1143f5df7f824b6dfc5b258e404e17002fcf3a102fd7b6331ed9c1dbfcbbbcb66aacbb204c88cdebe5f4daa6bbb7e7e7e8cd80b87c694a197d8e2bdb4271b046d067eb3f255f3467954dd2e2eb559a6ac9c5b34d9e683effb789be12ee0d39656730afe475318e34d56430f23f39fbdcc5e79e8b3589d752374fee603bcd691c7c753f6b5adccefb6af7c15407986eea186aa691f4136bba11a21bef7f69f0a0e1972a528cbc588796ee759c22f16da185a45c840fda2a1b0aa42f7eb7f8a7946a2ed08e7d19beedc4f9743f626e5eab4a8806de9e65ae3740f616204d1ed87555e81bd562b2391f0cbd6cb37fe50591d1cabb6345723288bfd16d94b604a47aab164e4d89bb53c04b16e59fecf6722ac2dfdd0d4aef8526a8eeba691f3f2146bf92d90ad5ef2dc6b3a58536c6660e850854976471c968da02e5b952ad853f625a0eb9da9e63e148bd4f76af69b52fde4e5f702fd8e965b1617f12933b68047a349c4246e556e75de52106b9050aaf4854d84c4f374c434184e3aeec0bf22a207d1c01e0b7b22932cdf941622bb8892897e02f697888eab8ddc3693fa15f79856cb95deeb2d5ef634950e7be9118689b390aa40a6dec417f7dc7aef403e2e908c5832c78321301a07d2ce2fa2b0d253e360d4211fd3f37072d09839bd2d584224036338514b21b3944fdd2ab388ce005108e13936a02f4b7cd2dbc1cc20824b9a4f700ef5329c3fc85e2f0486c0580745e3f917fa6f52abca2dfc6b2a96834751f35205e5c44414de45b3f9e566af58e2e29112fe0ff485aca84baae03b569b916625fdc3fb1f16031094be41be3a9fcc0f9482dcd5b1f40836dcf4d66101ab70862177d74739138e2d23f09bc612083be86d94dc99f874ff6d78385f0dca4e69826003f9380c0301df64fcb687d726b524ca0a85a4f131af2ad16b5e5bc31c8abb3898a445576ecc422e46bbd8637ea49c28ad412067f2eb1c79c6eed9fb43d523666aa3f833f88a8545d1e3f4866e4dc1b6a3bb3ed8630d5436177658c925966bc570129e53fc52807f0ace8718e7bd8831824ef930dc24606e1b53d1c911ad72ff2b75f50e968f50e8014dccb66436cd5c6ba26139a11b45972742d8200bdec9c3f68a91a29ff82598c78a5b83a14ccc98574ac14e1b37db2ced066877c8ed6e06e95ff15134f32ba9987ef3e143f56a9a74761864da0457f2051ea9609ccdb8a143ed1ef180a8421f06de5fbe5492b528707e4b3f4f1d75c6952dd8ae15a255612fbc182a0cef5a1c83fcd11580dfaa75b6b26c84ab3911fd8680da535a16da4e970cbc423762ce1d637d95edef6784bb2fc03e366d3c6c73989c17b59d8b0d7f4d88e003efd733fe5a322a608b9e3f27e53a3874d0c78fbc23f8b2e3248d9b2dc4496fadcaa2c424569bcdabd2bb8165d215f9fdc0512aaedb53bc3fa86d557553882cb9be4299ce81674eda72098e51d7a273fd83fb1aa8727c38eb6e394a4af9afa5a1cd12838f51f583f792ad3204d722ccd0f1f0a3ae5ea91d2ecb11ab5e3511666d5b05abba8ed3f2bbda77c6718503ada44eb59ccee0535a1b5d57a08df8b6d79f4e221cf610ee2530a3369b5a61c3f020d5893a9b8331af6a2c080fd25a0dc0b144a17961a5f53f47ff308cdf091ca34a56a9951f4b5fde215f52462983fa2bd85ed4440165a893fa1e1f75cc51fb84e00602a285882d6443f2142547e82c736d0ea885c186cbd3f0674b0877dfd365bb19175c956b8223e0cf97bc12b3f33ac0e65068371e1029a2211f983f778a15e5af8c71bf802c5f7695277a97abd06e15073a75fd3066cf904c30fa7f6effb1a3f7fd4bf5396ca23d4a2bb0af5426934b576c478b6ab26f923e2a4596e1d0a876e73a33c46f14eb2a5151042f2696874c8341ebdfed6384bdd8769e8a050a5f34315e6df52783cf7b0d0693f58d33a1a1e08f4fa2cef347a3fe14e3ff1b3133f0d2f9ef14b6d1e0c84ac838c58b238849b3f4643d7a04db9dad0cf78fe44c03fe364eabfea17b8e121ef3fc45706ab86e2d3f96238e225924b0008fea2808b2be680f20bba9f3a407014b5270e1e317f17223fe0ae1dade97aba79a56243bb3184e705c417828378c1acb8b82f5efa1d682ba18acbdf47953ce5cdc2b57aee2e1473d92dd248f4febaad487bd4b49ec284c73862a93ded193ff7c5f042135f953a91f2caded9fb797bc8448b4967f3126e7cee704ff86cfff6993714cbf8fb121957a3200c27f07e7ce74fc7664d92ef40bbb7e44f330116ec8428013b0ffa92785d993dece433988b597c8aeeeb4386fe2d7a8a03fc7e1fc90bd203524aa8d3e2a4b92736454fcb3fae4761438783052007bfc139c917ba31a33fe36c6c7585c6bc085683ea0c87e35888bc699aa600dffa3282223ff908b6b9e81742aabcee6c0bcc3ffa00be3f6cb5bddb1e30b26cf7d183454c8e1d71f32cb16ca771f3bafc96d91db6ada1c19e3904fb1efb6e76b2cb1dfb8b394a1a2d65842d3077ee7edf4c0f6df4e2661276c2c1e5f8333f255f874a1de93f7329de69cf672f99d0bdd7df3f7315b21cd997fa6036c20bb49f03935dada268516ebb4524eb52f50e9b3496b865ef58d0f60e3f8c1849d0398ee1760cc35ba30aad776a20f65afe0bc5967d9adbfb222a290ebb50a4d4ad15585f16f34708d1eda480b4cb2be4412144ce3f51193f37260f13eb0cd947d64559b37c1f4ad39a4e76795514f85f5624ef8e114158b2e58ece92fc5d162f45ea3be4e8aa6470bab3e70b5f6ca02a31447f0ed0ec6a82443ef291313f180c9a7bbcd1bd3a7416995f04cee711f4200ac9ee08a775c8157a9f58834239d2e9f02fc87007a1193a0e9cf8042cf001aa5976cc34fc7ce58038693f7a8971a2bccd94a3e6ec02e567dccc1a0760f773b7a806762cef9c43df99ce94b268423b4ace29d70e1d7ccaca5984977c6fe46d30f284d9a828ff70beb9beac895d3f4b50d506b6c005e9996dcb11316c3544360846289aff397262d5f77bd5b5b1b27edad0ad6ee0142d23f7f269417207a3725d8c5e2b56ea7ed08b2bb640964bfd4cde01075eafc10be6c98978a9a466b46c9f743d8687fe3f9f32aeed381cf393c72888fd2d4004397444b6a80f6d7f35c0dea3b8e4115c2900159671b30cd9835480aa5d7b2d2ec766b197fed02630bb41102822358e980e5eb5ab37bee0074af26af5b0ca8b19a4254040fac1823e7fcbc75c3fd12d55dca20437f321638c993fa34f3107202ff092c69b2bf4339e79a5fe2349ba609f5ae81d33398b8e6bd88c0349eb91528394c660482eb5e10642982e4ffdc0b358cd01017b6e4a47ab6aa3da770f7bfa9e981ff8b1d957a79695d1138b10c3cb9be591466532df5cde83a298dfbd8ac85f31297c3cbc7bd4cc5b49d37362692fe4ac1d35729fb52e3fc074c0fcc2d233a23f1a9ed19913a283b3249a0a9457ee94c61046d5e01903e6321db2b0162d04797c46eafda0063f170628597f5f3ba9764e3d1b3941ed8b6671621da676f052c43bf98b0f8067b1b8e8a66303a2d503e63b1170694b3dfe1cfafac485503f5ad7fdbce24c2134842d265eb9d2c8a632f17a82686cffc42c6f2e97c5483c4dc82e83c49b80563fc2562645711762edffecd22e23ab1aa1110da7cd71e760891f93d95b2ffe3eaffdf28e0cf1ff24d2e5b40f519285bad5174ca08c65e6c2e4a26c157e95e9b7f3ffd21bf308dd5897dd287a5c9343bc6d766862b202f85659486737615afe088741f55719c47c21a2507f8e2d9c84c1cf8bd0a5f95f5d78065f6426c518050e230710bb33c33be3cf23a8d149414fe657d0f176a409c7ae7995e23a714f145664619689f54eb6f9af6f13587579a91a007c975f5d7e5bcf6655df830250b8c3a17ecdc83ff07bb0e80c53b18adab860b4f5ca79942b3faf9bb6cd83a5abae9694cc1ae0c6ee3142f08e21b61e7c5a082223c1be887456a1ab3f0f3b6d84e963526d2cf2f7243156c92f4679451f3bd9b919a677f82c33dabafc97656e5ce72cdf265f5bee16bd3fa872fa4363d11812fb016da1aaae87ad9286ffdb27e99ce9fce7ac6942fc663f17a66a0bf2f9c6ebd59ac9470826d02fb4a1f5b83f7e71bf3fa209a521dce822b5c5dce52b4f928948f01c5929dd13f37571af3f3cd9b24b737db3a5ad6d213d0c0939fe3bcdd8378287115c97b1038bdc8b954c29fb3655986b99b8873a22c2f9782162c3e1a0ef3bb266b00eba4040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040404040";
//sTextoPlanoEnHexa = "f0f0f0f0f0f3f2f7f6f7f05ff0f0f1f1f2f2f3f35ff1f0f0f0f0f0f0f05ff05ff05ff05ff05ff05ff05ff0f7f05fff";
//System.out.println("Trama ebcdic en Hexa: " + sTextoPlanoEnHexa);
/*
System.out.println("Texto plano a encriptar es : " + sTextoPlanoEnHexa);
//Encripta con 3DES
String sTextoEn3DES = Crypto.encripta3DESede2Key(sTextoPlanoEnHexa, claveDES_A, claveDES_B);
System.out.println("cadena encriptada con 3des es : " + sTextoEn3DES);   
*/
//Encripta con DES
String sTextoEnDES = Crypto.encriptaDES(sTextoPlanoEnHexa, claveDES_A);

//System.out.println("cadena encriptada con des es : " + sTextoEnDES);
sTextoEnDES = sTextoPlanoEnHexa;
//byte[] bTextoEnDESEnAscii = ToAscii2(sTextoEnDES); 

//s = Crypto.ToHexa(bTextoEnDESEnAscii);
String desencriptado = Crypto.desencriptaDES(sTextoEnDES, claveDES_A);
//System.out.println("Trama desencriptada DES en Hexa: " + desencriptado);
byte[] asciiD = Crypto.ToAscii2(desencriptado);
//System.out.println(new String(Crypto.EbcdicToAscii(asciiD)));
/*
//Texto Desencriptado desde 3DES
sTextoPlanoEnHexa = Crypto.desencripta3DESede2Key(sTextoEn3DES, claveDES_A, claveDES_B);
System.out.println("cadena desencriptada desde 3des es  : " + sTextoPlanoEnHexa);   

//Texto Desencriptado con 3DES
sTextoPlanoEnHexa = Crypto.desencriptaDES(sTextoEnDES, claveDES_A);
System.out.println("cadena desencriptada desde des es  : " + sTextoPlanoEnHexa);   


//String texto3DESEnAscii = EbcdicToAscii(Crypto.ToAscii2(cad1));
//System.out.println("cadena desencriptada con 3des en Ascii es : " + texto3DESEnAscii);

String textoMAC = "000000000066666000000007767676ALFAOMEGA                     0661";
byte textoMACEnHexa[] = AsciiToEbcdic(textoMAC);
String textoMACEnHexa1 = Crypto.ToHexa(textoMACEnHexa);
String macHexa = Crypto.getMac(textoMACEnHexa1, claveMac);
System.out.println("mi mac final en hexa es : " + macHexa);   
byte[] macAscii = Crypto.ToAscii2(macHexa);
System.out.println("mi mac final en ascii es : " + new String(macAscii));
*/

}

/** Cifra arreglo de bytes b (en ascii), usando llave (en ascii) y sin aplicar padding
 *  usando algoritmo DES
 */
private static byte[] cifrabloqueDesNoPad(byte[] b,byte[] llave){
  byte cifrado[] = null;  
  try {
      
      Key key = null;
//      SecretKeyFactory skfDesNoPad = SecretKeyFactory.getInstance("DES");
//      SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
      Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
      skfDesNoPad = SecretKeyFactory.getInstance("DES");
//      MaskCenter.getFrameBranch().skfDesNoPad = SecretKeyFactory.getInstance("DES");
//      key = MaskCenter.getFrameBranch().skfDesNoPad.generateSecret(new DESKeySpec(llave));
      key = skfDesNoPad.generateSecret(new DESKeySpec(llave));
      cipher.init(Cipher.ENCRYPT_MODE, key);
      
      cifrado = cipher.doFinal(b);
      return cifrado;
      }
  catch(Exception e){System.out.println("Metodo cifrabloqueDES : "+e.getMessage());
  log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
  }  
  return cifrado;
}

/** Cifra arreglo de bytes b (en ascii), usando llave (en ascii) y aplicando padding
 *  PKCS5Padding y algoritmo DES y modo ECB
 */
private static byte[] cifrabloqueDesPad(byte[] b,String llave){

  byte cifrado[] = null;  
  try {
      
      Key key = null;
//      SecretKeyFactory skfDesPad = SecretKeyFactory.getInstance("DES");
      Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
      skfDesPad = SecretKeyFactory.getInstance("DES");
      key = skfDesPad.generateSecret(new DESKeySpec(llave.getBytes()));
      cipher.init(Cipher.ENCRYPT_MODE, key);
      cifrado = cipher.doFinal(b);
      return cifrado;
      }
  catch(Exception e){System.out.println("Metodo cifrabloqueDES : "+e.getMessage());
  log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
  }  
  return cifrado;
}

/** Descifra arreglo de bytes b (en ascii), usando llave (en ascii) y sin aplicar padding
 *  usando algoritmo DES.
 */
private static byte[] descifrabloqueDesNoPad(byte[] b,byte[] llave)
{
  byte descifrado[] = null;  
  try {
      
      Key key = null;
//      SecretKeyFactory skfCifNoPad = SecretKeyFactory.getInstance("DES");
      Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
      skfCifNoPad = SecretKeyFactory.getInstance("DES");
      key = skfCifNoPad.generateSecret(new DESKeySpec(llave));
      cipher.init(Cipher.DECRYPT_MODE, key);
      descifrado = cipher.doFinal(b);
      return descifrado;
      }
  catch(Exception e){System.out.println("Metodo descifrabloqueDES : "+e.getMessage());
  log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
  }  
  return descifrado;
}
/** Aplica XOR a los arreglos b1,b2 devolviendo arreglo con el XOR
 * y de la misma longitud que los primeros
 */
private static byte[] getxor(byte[] b1, byte[] b2){

    int n = b1.length;
    byte[] b3 = new byte[n];
    for(int k=1;k<=n;k++)
    {
          b3[k-1] = (byte)(b1[k-1] ^ b2[k-1]); 
    }
    return b3;
}
/** Devuelve los n primeros bytes del arreglo b.
 *  tomandolos de izquierda a derecha.
 */
public static byte[] getbytes(byte[] b,int n)
{
    byte[] c = new byte[n];
    for(int i=1;i<=n;i++)
    {
        c[n-i]=b[n-i];
    }
    
    return c;
}
/** Metodo al cual se le pasa com parametro la cadena en hexadecimal, y el nro
 * de bloque que se desea obtener (la cadena se convierte a ascii, y se obtiene
 * bloque de 8 bytes de la posicion indicada. 
 * 
 */
private static byte[] bloque(String cadena, int bloque){

  byte[] texto; 
  texto = ToAscii2(cadena); 
    
  byte[] b = null;
  int n = (bloque*8)- texto.length; 
  if(n > 0)  {
  	b = new byte[8-n];   
  }else if(n<=0 ){
    b = new byte[8];   
        }
  
  if(n<=0){
  	for(int i=1;i<=8;i++){
  		b[i-1] = texto[(bloque-1)*8+(i-1)];  
  	}
  }else{
  	for(int i=1;i<=(8-n);i++){
  		b[i-1] = texto[(bloque-1)*8+(i-1)];  
  	}
  }
  return b;
}
/** Se obtiene el bloque de 8 bytes de cadena (cadena esta en hexadecimal), si el ultimo bloque
 * es menor a 8 bytes, se completa con ceros por el lado derecho (en los digitos menos significativos ) 
 */
private static byte[] bloqueMac(String cadena, int bloque){

  int cero = 0;
  byte[] texto; 
  texto = ToAscii2(cadena); 
    
  byte[] b = new byte[8];
  int n = (bloque*8)- texto.length; 
  
  if(n<=0)
  {
     for(int i=1;i<=8;i++)
     {
       b[i-1] = texto[(bloque-1)*8+(i-1)];  
     }
  }
  else
  {
     for(int j=1;j<=n;j++)   
     {
      b[j-1] = (byte)cero;   
     }
     for(int i=1;i<=(8-n);i++)
     {
      b[i-1+n] = texto[(bloque-1)*8+(i-1)];  
     }
  }
  return b;
}

/** Metodo al cual se le pasa com parametro la cadena en hexadecimal, y la llave
 * en hexadecimal y devuelve un string con la cadena encriptada usando DES en Hexadecimal.
 * 
 */
public static String encriptaDES(String cadena, String llave){
  String retorno = "";
  byte[] texto = ToAscii2(cadena);
  byte[] claveA = ToAscii2(llave);
  int n = 0;
  int m = 0;
  int l = 0;
   
  n = (texto.length / 8);
  m = (texto.length - 8*n);
  n--;
  if(m > 0){n++;}
  l = texto.length;
  
  if(l < 8){
        byte[] a = ToAscii2("0000000000000000");
        byte[] d = cifrabloqueDesPad(a,new String(claveA));
        byte[] x = new byte[l];
        x = getbytes(d,l);
        byte z[] = getxor(x,texto);
        String cad2 = ToHexa(z);
        return cad2;  
  }
  
  try {
  	byte cifrado[];
    byte cifrado2[];
    byte[] inicial;
    inicial = bloque(cadena,1);
  
    cifrado = cifrabloqueDesNoPad(inicial,claveA);
    retorno = retorno + ToHexa(cifrado);
    cifrado2 = cifrado;  
  
	if(n==0){          
	      //aqui va cuando longitud ascii a evaluar es menor a 8 bytes        
	}else if(n > 0){
		for(int i=1;i<=n;i++){
	        if(i==n){
	        	if(m==0){
	        		cifrado2 = getxor(cifrado, bloque(cadena, i+1));
	        		cifrado = cifrabloqueDesNoPad(cifrado2, claveA);   
	        		retorno = retorno + ToHexa(cifrado);
	        		return retorno;
	        	}
	            cifrado = cifrabloqueDesNoPad(cifrado,claveA); 
	        }   
	        byte[] c = bloque(cadena,i+1);
	        byte cipfinal[] = {};
	        if(c.length == 8){
	        	cifrado2 = getxor(cifrado,c);           
        		cipfinal = cifrabloqueDesNoPad(cifrado2,claveA);
	        }else{
	        	byte[] cc = getbytes(cifrado,c.length);   
	        	cifrado2 = getxor(cc,c);    
	        	retorno = retorno + ToHexa(cifrado2);
	        	return retorno;
	        }        
	        cifrado = cipfinal;
	        retorno = retorno+ToHexa(cipfinal);
	    }     
	 }  
  }catch(Exception e){
	  log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
  	System.out.println("Metodo encripta : "+e.getMessage());
  }
  return retorno;  
}


/** Metodo al cual se le pasa com parametro la cadena en hexadecimal, y la llave
 * en hexadecimal y devuelve un string con la cadena desencriptada usando DES en Hexadecimal.
 * 
 */
public static String desencriptaDES(String cadena,String llave){

  String retorno = "";
  
  byte[] texto; 
  byte[] claveA;
  int n = 0;
  int m = 0;
  int l = 0;
  texto = ToAscii2(cadena);  
  claveA = ToAscii2(llave);
  
  n = (texto.length / 8);
  m = (texto.length - 8*n);
  n--;
  if(m > 0){n++;}
  l = texto.length;
  
   if(l < 8)
  {
        byte[] a = ToAscii2("0000000000000000");
        byte[] d = cifrabloqueDesPad(a,new String(claveA));
        byte[] x = new byte[l];
        x = getbytes(d,l);
        byte z[] = getxor(x,texto);
        String cad2 = ToHexa(z);
        return cad2;  
  }
  
  try {
      
       byte cifrado[];
       byte cifrado2[];
       byte[] inicial;
       inicial = bloque(cadena,1);
       cifrado = descifrabloqueDesNoPad(inicial,claveA);
       
       retorno = retorno + ToHexa(cifrado);
       cifrado2 = cifrado;  
  
  if(n==0) 
  {          
          
  }
  else if(n > 0) 
  {
      
      for(int i=1;i<=n;i++)
      {
          if(i==n) 
          { 
             byte[] d;
             byte[] e;
            
             if(m==0)
             {
               cifrado = descifrabloqueDesNoPad(bloque(cadena,i+1),claveA);   
               cifrado2 = getxor(cifrado,bloque(cadena,i));
               retorno = retorno + ToHexa(cifrado2);
               return retorno;
             }
             else
             {
               cifrado = cifrabloqueDesNoPad(bloque(cadena,i),claveA);   
               d = getbytes(cifrado,bloque(cadena,i+1).length); 
               e = getxor(d,bloque(cadena,i+1));
               retorno = retorno + ToHexa(e);
               return retorno;
             }
            
          }   
            
          byte[] c = bloque(cadena,i+1);
          byte[] cipfinal;        
          if(c.length == 8)
          {
            cipfinal = descifrabloqueDesNoPad(c,claveA);
            cifrado2 = getxor(cipfinal,bloque(cadena,i));           
            retorno = retorno + ToHexa(cifrado2);
          }
          else
          {
            
            byte[] cc = getbytes(cifrado,c.length);   
            cifrado2 = getxor(cc,c);    
            retorno = retorno + ToHexa(cifrado2);
            return retorno;
          }         
          cifrado = cipfinal;                          
      }     
  }  
  }
  catch(Exception e){System.out.println("Metodo desencripta : "+e.getMessage());
  log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
  }
  
  return retorno;  
}

public static byte[] desencriptaTramaDES(byte[] bDatos, String sClave){
	
	String textoEncriptadoDES = Crypto.ToHexa(bDatos);
	String cad = Crypto.desencriptaDES(textoEncriptadoDES, sClave);	
	byte[] trama = Crypto.ToAscii2(cad);
	
	return trama;
}

public static byte[] desencriptaTrama3DES(byte[] bDatos, String sClave_A, String sClave_B){
	
	String textoEncriptadoDES = Crypto.ToHexa(bDatos);
	String cad = Crypto.desencripta3DESede2Key(textoEncriptadoDES, sClave_A, sClave_B);	
	byte[] trama = Crypto.ToAscii2(cad);
	
	return trama;
}

/** Convierte el arreglo de bytes a Hexadecimal, lo devuelve como String
 * 
 */
public static String ToHexa(byte[] in) {

    String ret = "";
    if (in != null){
		int size = in.length;
		if (size != 0){
			for (int i = 0; i < size; i++){
				String sss = Integer.toHexString(in[i]);
				if (in[i] < 0){
	 			    ret += sss.substring(6,8);
				}else{
	                if (in[i] <= 15)
	                	sss = "0" + sss;
					ret += sss;
			    }
			}
		}
	}
	return ret;
}

/** Convierte la cadena en Hexadecimal, a su representacion en Ascci pero como arreglo de bytes.
 *
 */
 public static byte[] ToAscii2(String hex) {
    String cad = "";
    String hex2 = hex;
    
    int n = hex.length();
    byte[] bb = new byte[n/2];
    int m = 0;
    n = n/2;
    for(int i = 1; i <= n; i++){
    	cad = hex2.substring(0,2);
    	hex2 = hex2.substring(2);
    	m = Integer.parseInt(cad,16);  
    	bb[i-1] = (byte)m;         
    }          
    return bb;
}

 /** 
 *	Convierte la cadena Hexadecimal, a su representacion en Ebcdic. Solo para 0-9 y A-F. Se retorna como String.
 */
 public static String ToEbcdic(String hex) {
    Hashtable hashHexaEbcdic = new Hashtable();
    hashHexaEbcdic.put("F0", "0");hashHexaEbcdic.put("F1", "1");hashHexaEbcdic.put("F2", "2");hashHexaEbcdic.put("F3", "3");hashHexaEbcdic.put("F4", "4");
    hashHexaEbcdic.put("F5", "5");hashHexaEbcdic.put("F6", "6");hashHexaEbcdic.put("F7", "7");hashHexaEbcdic.put("F8", "8");hashHexaEbcdic.put("F9", "9");
    hashHexaEbcdic.put("C1", "A");hashHexaEbcdic.put("C2", "B");hashHexaEbcdic.put("C3", "C");hashHexaEbcdic.put("C4", "D");hashHexaEbcdic.put("C5", "E");
    hashHexaEbcdic.put("C6", "F");hashHexaEbcdic.put("40", " ");
    
    int iLen = hex.length();
    String sEbcdic = "";
    hex = hex.toUpperCase();
    
    for(int i = 0; i <= iLen-1; i++){
    	sEbcdic += hashHexaEbcdic.get(hex.substring(i, i+2)).toString();
    	i++;
    }          
    return sEbcdic;
}
/** Metodo que ingresando como parametros la cadena en Hexadecimal, y la llave
  *  en  Hexadecimal, aplica el estandar Ansi X9.9-1 y halla la Mac y la devuelve
  *  en Hexadecimal.
  */
public static String getMac(String cadena, String llave){

  String retorno = "";
  byte[] texto = ToAscii2(cadena);
  byte[] claveA = ToAscii2(llave);
  int n = 0;
  int m = 0;
  int l = 0;
   
  n = (texto.length / 8);
  m = (texto.length - 8*n);
  l = texto.length;
  byte[] b1 = null;
  byte[] b2 = null;
  
  try {
      
       if(m==0)
       {
           for(int i=1;i<=n;i++)
           {
            if(i==1)
            {    
               b1 = bloqueMac(cadena,i);
            }
            
            b2 = cifrabloqueDesNoPad(b1,claveA);
            if(i < n)
            {
             b1 = getxor(b2,bloqueMac(cadena,i+1));
            }
            else
            {
             b1 = getbytes(b2,4);
             retorno = ToHexa(b1);
             return retorno;             
            }    
               
           }           
       }
       else
       {
        n++;
           for(int i=1;i<=n;i++)
           {
             if(i==1)
             {    
               b1 = bloqueMac(cadena,i);
             }
            
             b2 = cifrabloqueDesNoPad(b1,claveA);
             if(i < n)
             {
              b1 = getxor(b2,bloqueMac(cadena,i+1));
             }
             else
             {
              b1 = getbytes(b2,4);
              retorno = ToHexa(b1);
              return retorno;             
             }              
           }                     
       }
  }
  catch(Exception e){System.out.println("Metodo getMac : "+e.getMessage());
  log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
  }
  return retorno;      
}

/** Metodo que ingresando como parametros cadena, la cual esta en hexadecimal y es de tipo 
 *  String, y las llaves A y B, ambas en hexadecimal, me aplica el algoritmo 3DESede
 *  devolviendome la cadena encriptada en su representacion Hexadecimal.
 */
public static String encripta3DESede2Key(String cadena,String llave1,String llave2){
  String retorno="";  
  String cad = "";
  
    retorno = encriptaDES(cadena,llave1);
    cad = desencriptaDES(retorno,llave2);
    retorno = encriptaDES(cad,llave1);
    
  return retorno;
}

/** Metodo que ingresando como parametros cadena, la cual esta en hexadecimal y es de tipo 
 *  String, y las llaves A y B, ambas en hexadecimal, me aplica el algoritmo 3DESede
 *  devolviendome la cadena desencriptada en su representacion Hexadecimal.
 */
public static String desencripta3DESede2Key(String cadena,String llave1,String llave2){
    String retorno="";
    String cad = "";
    retorno = desencriptaDES(cadena,llave1);
    cad = encriptaDES(retorno,llave2);
    retorno = desencriptaDES(cad,llave1);
    
    return retorno;
}

/** Metodo al cual le ingreso una cadena en EBCDIC como String, y me devuelve un arreglo de bytes
 *  el cual es su representacion a ASCII.
 *
 */
public static byte[] AsciiToEbcdic(String ebcdic){
 byte[] cad = null;    
 try {
    return ebcdic.getBytes(encoding);    
 }catch(Exception e){
	 log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
 	System.out.println("AsciiToEbcdic: "+e.getMessage());}
 return cad;
}

public static String EbcdicToAscii(byte[] ascii ){
 //printArrayByte(ascii,"405");
 byte[] rpta;
 try {
 	ByteArrayOutputStream bRptaEBCDIC = new ByteArrayOutputStream(ascii.length);
 	ByteArrayOutputStream bRptaASCII = new ByteArrayOutputStream(ascii.length);
 	
 	int inicioDatos = -1;
 	int finDatos = -1;
 	int inicioNoDatos = -1;
 	boolean buscandoInicioDatos = true;
 	for (int i=0; i<= ascii.length - 1; i++){
 		if (buscandoInicioDatos){
 			if ((ascii[i] < -1) || (ascii[i] > 31)){
 				if (inicioNoDatos > -1){
	 				bRptaASCII.write(ascii, inicioNoDatos, i-inicioNoDatos);
	 				inicioNoDatos = -1;
 				}
 				inicioDatos = i;
 				buscandoInicioDatos = false; 
 			}else{
 				if (inicioNoDatos == -1){
 					inicioNoDatos = i;
 				}
 			}
 		}else{
 			if ((ascii[i] >= -1) && (ascii[i] <= 31)){
 				finDatos = i;
 				bRptaEBCDIC.reset();
 				bRptaEBCDIC.write(ascii, inicioDatos, finDatos-inicioDatos);
 				bRptaASCII.write( new String(bRptaEBCDIC.toByteArray(), encoding).getBytes());
 				buscandoInicioDatos = true; 
 				i--;
 			}else{
 				if (i == ascii.length - 1){
	 				bRptaEBCDIC.reset();
	 				bRptaEBCDIC.write(ascii, inicioDatos, i-inicioDatos);
	 				bRptaASCII.write( new String(bRptaEBCDIC.toByteArray(), encoding).getBytes());
 				}
 			}
 		}
 			
 		
 	}
 	//printArrayByte(bRptaASCII.toByteArray(),"406");
 	return bRptaASCII.toString();
 }
 catch(Exception e){System.out.println("AsciiToEbcdic : "+e.getMessage());
 log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
 }
 return ""; 
}

	private static void printArrayByte(byte[] trama,String nombre){
	    File f = null;
	    
	    if (Constante.CONSTANTE_OS.equals("1")){
		    //String archivo = "/home/logs/"+nombre+"__"+ObjectUtil.getCodigoTRX()+".txt";
	        String archivo = "/home/logs/"+nombre+".txt";
			f = new File(archivo);
		}
		else if (Constante.CONSTANTE_OS.equals("2") || Constante.CONSTANTE_OS.equals("3")){
		    String archivo = "/home/logs/"+nombre+".txt";
		    f = new File(archivo);
		}
		String result = "";
		for (int i = 0; i < trama.length; i++) {
		    result += String.valueOf(trama[i]) + "\r\n";
        }
		
		try {
			FileOutputStream st = new FileOutputStream(f);
			st.write(result.getBytes());
			st.close();
			f = null;
			st = null;
		} catch (Exception e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			System.out.println(""+e);
		}
	}

}

