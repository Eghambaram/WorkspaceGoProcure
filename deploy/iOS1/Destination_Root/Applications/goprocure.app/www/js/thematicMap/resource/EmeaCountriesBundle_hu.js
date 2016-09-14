"use strict";var l={"SHN":["SHN","Szent Ilona"],"ROU":["ROU","Rom\u00E1nia"],"DJI":["DJI","Dzsibuti"],"KGZ":["KGZ","Kirgiziszt\u00E1n"],"MOZ":["MOZ","Mozambik"],"NOR":["NOR","Norv\u00E9gia"],"SYR":["SYR","Sz\u00EDria"],"UZB":["UZB","\u00DCzbegiszt\u00E1n"],"FRO":["FRO","Fer\u00F6er-szigetek"],"ARE":["ARE","Egyes\u00FClt Arab Emir\u00E1tusok"],"SYC":["SYC","Seychelle-szigetek"],"ARM":["ARM","\u00D6rm\u00E9nyorsz\u00E1g"],"MNE":["MNE","Montenegr\u00F3"],"GEO":["GEO","Gr\u00FAzia"],"TJK":["TJK","Tadzsikiszt\u00E1n"],"SAU":["SAU","Sza\u00FAd-Ar\u00E1bia"],"TKM":["TKM","T\u00FCrkmeniszt\u00E1n"],"SCG":["SCG","Szerbia"],"KWT":["KWT","Kuvait"],"JOR":["JOR","Jord\u00E1nia"],"MLT":["MLT","M\u00E1lta"],"UKR":["UKR","Ukrajna"],"NAM":["NAM","Nam\u00EDbia"],"TZA":["TZA","Tanz\u00E1nia"],"MKD":["MKD","Maced\u00F3nia"],"SDN":["SDN","Szud\u00E1n"],"GHA":["GHA","Gh\u00E1na"],"SEN":["SEN","Szeneg\u00E1l"],"GIB":["GIB","Gibralt\u00E1r"],"BGR":["BGR","Bulg\u00E1ria"],"AUT":["AUT","Ausztria"],"STP":["STP","Saint Tome \u00E9s Principe"],"SWZ":["SWZ","Szv\u00E1zif\u00F6ld"],"ZAF":["ZAF","D\u00E9l-Afrika"],"DZA":["DZA","Alg\u00E9ria"],"CHE":["CHE","Sv\u00E1jc"],"BHR":["BHR","Bahrein"],"SWE":["SWE","Sv\u00E9dorsz\u00E1g"],"CZE":["CZE","Cseh K\u00F6zt\u00E1rsas\u00E1g"],"UNN":["UNN","Semleges z\u00F3na (ENSZ ellen\u0151rz\u00E9s\u0171)"],"CIV":["CIV","Elef\u00E1ntcsontpart"],"NLD":["NLD","Hollandia"],"CYP":["CYP","Ciprus"],"AFG":["AFG","Afganiszt\u00E1n"],"SVK":["SVK","Szlov\u00E1kia"],"SVN":["SVN","Szlov\u00E9nia"],"BIH":["BIH","Bosznia-Hercegovina"],"YEM":["YEM","Jemen"],"COG":["COG","Kong\u00F3"],"COD":["COD","Kong\u00F3i Demokratikus K\u00F6zt\u00E1rsas\u00E1g"],"RWA":["RWA","Ruanda"],"GOL":["GOL","Gol\u00E1n-fenns\u00EDk"],"FIN":["FIN","Finnorsz\u00E1g"],"TCD":["TCD","Cs\u00E1d"],"REU":["REU","Reunion-szigetek"],"HRV":["HRV","Horv\u00E1torsz\u00E1g"],"COM":["COM","Comore-szigetek"],"TUN":["TUN","Tun\u00E9zia"],"AZE":["AZE","Azerbajdzs\u00E1n"],"TUR":["TUR","T\u00F6r\u00F6korsz\u00E1g"],"POL":["POL","Lengyelorsz\u00E1g"],"GNQ":["GNQ","Egyenl\u00EDt\u0151i Guinea"],"NGA":["NGA","Nig\u00E9ria"],"WES":["WES","Ciszjord\u00E1nia"],"GNB":["GNB","Bissau-Guinea"],"PAK":["PAK","Pakiszt\u00E1n"],"MDG":["MDG","Madagaszk\u00E1r"],"HUN":["HUN","Magyarorsz\u00E1g"],"EGY":["EGY","Egyiptom"],"BLR":["BLR","Belorusszia"],"MDA":["MDA","Moldovai K\u00F6zt\u00E1rsas\u00E1g"],"ALB":["ALB","Alb\u00E1nia"],"CMR":["CMR","Kamerun"],"OMN":["OMN","Om\u00E1n"],"CAF":["CAF","K\u00F6z\u00E9p-afrikai K\u00F6zt\u00E1rsas\u00E1g"],"PRT":["PRT","Portug\u00E1lia"],"ESH":["ESH","Nyugat-Szahara"],"ISR":["ISR","Izrael"],"KAZ":["KAZ","Kazahszt\u00E1n"],"ISL":["ISL","Izland"],"DNK":["DNK","D\u00E1nia"],"DEU":["DEU","N\u00E9metorsz\u00E1g"],"LBN":["LBN","Libanon"],"ITA":["ITA","Olaszorsz\u00E1g"],"LBR":["LBR","Lib\u00E9ria"],"LBY":["LBY","L\u00EDbia"],"QAT":["QAT","Katar"],"IRQ":["IRQ","Irak"],"ETH":["ETH","Eti\u00F3pia"],"IRN":["IRN","Ir\u00E1n"],"IRL":["IRL","\u00CDrorsz\u00E1g"],"EST":["EST","\u00C9sztorsz\u00E1g"],"MRT":["MRT","Maurit\u00E1nia"],"ESP":["ESP","Spanyolorsz\u00E1g"],"GRC":["GRC","G\u00F6r\u00F6gorsz\u00E1g"],"FRA":["FRA","Franciaorsz\u00E1g"],"CPV":["CPV","Z\u00F6ldfoki K\u00F6zt\u00E1rsas\u00E1g"],"LUX":["LUX","Luxemburg"],"GBR":["GBR","Egyes\u00FClt Kir\u00E1lys\u00E1g"],"LVA":["LVA","Lettorsz\u00E1g"],"GAZ":["GAZ","G\u00E1zai-\u00F6vezet"],"MAR":["MAR","Marokk\u00F3"],"LTU":["LTU","Litv\u00E1nia"],"SOM":["SOM","Szom\u00E1lia"],"RUS":["RUS","Oroszorsz\u00E1g"]};(this?this:window)['DvtBaseMapManager']['_UNPROCESSED_MAPS'][2].push(["emea","countries",l]);