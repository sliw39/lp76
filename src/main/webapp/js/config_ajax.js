var HANDLER = {
	processListAreas : function(datas) {
		var options = "";
		for(var i in datas.areas) {
			option += "<option>"+datas.areas[i]+"</option>";
		}
		$("#zone").html(options);
	},
	
	processListStations : function(datas) {
		var options = "";
		for(var i in datas.stations) {
			option += "<option>"+datas.stations[i]+"</option>";
		}
		$("#station").html(options); 
	},
	
	processListSensors : function(datas) {
		//TODO insert in grid !!!
	}
}

var REQUESTER = {
	/**
	 * Recupere la liste de toutes les Zones
	 */
	getAllAreas : function() {
		$.post( "requestAreas.do", function( data ) {
			HANDLER.processListAreas(data);
		});
	},
	
	/**
	 * Recupere la liste des stations d'une zone
	 * @param zone le nom de la zone ("ALL" = toutes les stations)
	 */
	getStationsById : function(zone) {
		$.post( "requestStations.do", { "area" : zone },function( data ) {
			HANDLER.processListStations(data);
		});
	},
	
	/**
	 * Recupere la liste des capteurs selon des filtres
	 * @param zone : la zone ou "ALL"
	 * @param station : la station ou "ALL"
	 * @param filter : un filtre texte
	 */
	getSensors : function(zone, station, filter) {
		$.post( "requestStations.do", { "area" : zone, "station":station, "filter":filter },function( data ) {
			HANDLER.processListSensors(data);
		});
	}
}
