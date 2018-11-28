package com.acuevas.bcnTrees.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.acuevas.bcnTrees.util.MapParserer;

/**
 * Tree class with all the fields from the XML structure
 *
 * @use XMLFile.toString for more info on the XML structure
 * @author Alex
 */
public class Tree {
//I have to initialize all the fields so i can get the Class with .getClass later on
	private String codi = "";
	private Double posicioX_ETRS89 = 0.0;
	private Double posicioY_ETRS89 = 0.0;
	private Double latitud_WGS84 = 0.0;
	private Double longitud_WGS84 = 0.0;
	private String tipusElement = "";
	private String espaiVerd = "";
	private String adreca = "";
	private String alcada = "";
	private Integer catEspecieId = 0;
	private String nomCientific = "";
	private String nomEsp = "";
	private String nomCat = "";
	private String categoriaArbrat = "";
	private Double ampladaVorera = 0.0;
	private LocalDate plantacioDT = LocalDate.of(1900, 01, 01);
	private String tipAigua = "";
	private String tipReg = "";
	private String tipSuperf = "";
	private String tipSuport = "";
	private String cobertaEscocell = "";
	private String midaEscocell = "";
	private String voraEscocell = "";
	// 23 properties

	/**
	 * @param properties. A List of all the properties in this class received from
	 *        SaxHandler. To initialize them I have a Map<Class,Function> With it i
	 *        can give him a String and a class type so it parses the String into
	 *        that specific class type.
	 * @See MapParserer
	 * @See SaxHandler
	 */
	@SuppressWarnings("unchecked")
	public Tree(List<String> properties) {
		codi = (String) MapParserer.getMap().get(codi.getClass()).apply(properties.get(0));
		posicioX_ETRS89 = (Double) MapParserer.getMap().get(posicioX_ETRS89.getClass()).apply(properties.get(1));
		posicioY_ETRS89 = (Double) MapParserer.getMap().get(posicioY_ETRS89.getClass()).apply(properties.get(2));
		latitud_WGS84 = (Double) MapParserer.getMap().get(latitud_WGS84.getClass()).apply(properties.get(3));
		longitud_WGS84 = (Double) MapParserer.getMap().get(longitud_WGS84.getClass()).apply(properties.get(4));
		tipusElement = (String) MapParserer.getMap().get(tipusElement.getClass()).apply(properties.get(5));
		espaiVerd = (String) MapParserer.getMap().get(espaiVerd.getClass()).apply(properties.get(6));
		adreca = (String) MapParserer.getMap().get(adreca.getClass()).apply(properties.get(7));
		alcada = (String) MapParserer.getMap().get(alcada.getClass()).apply(properties.get(8));
		catEspecieId = (Integer) MapParserer.getMap().get(catEspecieId.getClass()).apply(properties.get(9));
		nomCientific = (String) MapParserer.getMap().get(nomCientific.getClass()).apply(properties.get(10));
		nomEsp = (String) MapParserer.getMap().get(nomEsp.getClass()).apply(properties.get(11));
		nomCat = (String) MapParserer.getMap().get(nomCat.getClass()).apply(properties.get(12));
		categoriaArbrat = (String) MapParserer.getMap().get(nomCientific.getClass()).apply(properties.get(13));
		ampladaVorera = (Double) MapParserer.getMap().get(ampladaVorera.getClass()).apply(properties.get(14));
		plantacioDT = (LocalDate) MapParserer.getMap().get(plantacioDT.getClass()).apply(properties.get(15));
		tipAigua = (String) MapParserer.getMap().get(tipAigua.getClass()).apply(properties.get(16));
		tipReg = (String) MapParserer.getMap().get(tipReg.getClass()).apply(properties.get(17));
		tipSuperf = (String) MapParserer.getMap().get(tipSuperf.getClass()).apply(properties.get(18));
		tipSuport = (String) MapParserer.getMap().get(tipSuport.getClass()).apply(properties.get(19));
		cobertaEscocell = (String) MapParserer.getMap().get(cobertaEscocell.getClass()).apply(properties.get(20));
		midaEscocell = (String) MapParserer.getMap().get(midaEscocell.getClass()).apply(properties.get(21));
		voraEscocell = (String) MapParserer.getMap().get(voraEscocell.getClass()).apply(properties.get(22));
	}

	/**
	 * Transforms the LocalDate field "plantacioDT" into a String with format
	 * "dd/MM/yyyy", if the field is null returns "Null". Used in Tree.toString()
	 *
	 * @return String date
	 */
	public String localDateToString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		if (plantacioDT != null)
			return plantacioDT.format(formatter);
		return "Null";
	}

	/**
	 * hashCode for .equals among others
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codi == null) ? 0 : codi.hashCode());
		return result;
	}

	/**
	 * Checks if two objects are equal based on codi field.
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tree other = (Tree) obj;
		if (codi == null) {
			if (other.codi != null)
				return false;
		} else if (!codi.equals(other.codi))
			return false;
		return true;
	}

	/**
	 * Prints the tree in console.
	 */
	@Override
	public String toString() {
		return "Tree: [codi=" + codi + ", posicioX_ETRS89=" + posicioX_ETRS89 + ", posicioY_ETRS89=" + posicioY_ETRS89
				+ ", latitud_WGS84=" + latitud_WGS84 + ", longitud_WGS84=" + longitud_WGS84 + ", tipusElement="
				+ tipusElement + ", espaiVerd=" + espaiVerd + ", adreca=" + adreca + ", alcada=" + alcada
				+ ", catEspecieId=" + catEspecieId + ", nomCientific=" + nomCientific + ", nomEsp=" + nomEsp
				+ ", nomCat=" + nomCat + ", categoriaArbrat=" + categoriaArbrat + ", ampladaVorera=" + ampladaVorera
				+ ", plantacioDT=" + localDateToString() + ", tipAigua=" + tipAigua + ", tipReg=" + tipReg
				+ ", tipSuperf=" + tipSuperf + ", tipSuport=" + tipSuport + ", cobertaEscocell=" + cobertaEscocell
				+ ", midaEscocell=" + midaEscocell + ", voraEscocell=" + voraEscocell + "]";
	}

	/**
	 * @return the codi
	 */
	public String getCodi() {
		return codi;
	}

	/**
	 * @return the posicioX_ETRS89
	 */
	public Double getPosicioX_ETRS89() {
		return posicioX_ETRS89;
	}

	/**
	 * @return the posicioY_ETRS89
	 */
	public Double getPosicioY_ETRS89() {
		return posicioY_ETRS89;
	}

	/**
	 * @return the latitud_WGS84
	 */
	public Double getLatitud_WGS84() {
		return latitud_WGS84;
	}

	/**
	 * @return the longitud_WGS84
	 */
	public Double getLongitud_WGS84() {
		return longitud_WGS84;
	}

	/**
	 * @return the tipusElement
	 */
	public String getTipusElement() {
		return tipusElement;
	}

	/**
	 * @return the espaiVerd
	 */
	public String getEspaiVerd() {
		return espaiVerd;
	}

	/**
	 * @return the adreca
	 */
	public String getAdreca() {
		return adreca;
	}

	/**
	 * @return the alcada
	 */
	public String getAlcada() {
		return alcada;
	}

	/**
	 * @return the catEspecieId
	 */
	public int getCatEspecieId() {
		return catEspecieId;
	}

	/**
	 * @return the nomCientific
	 */
	public String getNomCientific() {
		return nomCientific;
	}

	/**
	 * @return the nomEsp
	 */
	public String getNomEsp() {
		return nomEsp;
	}

	/**
	 * @return the nomCat
	 */
	public String getNomCat() {
		return nomCat;
	}

	/**
	 * @return the categoriaArbrat
	 */
	public String getCategoriaArbrat() {
		return categoriaArbrat;
	}

	/**
	 * @return the ampladaVorera
	 */
	public Double getAmpladaVorera() {
		return ampladaVorera;
	}

	/**
	 * @return the plantacioDT
	 */
	public LocalDate getPlantacioDT() {
		return plantacioDT;
	}

	/**
	 * @return the tipAigua
	 */
	public String getTipAigua() {
		return tipAigua;
	}

	/**
	 * @return the tipReg
	 */
	public String getTipReg() {
		return tipReg;
	}

	/**
	 * @return the tipSuperf
	 */
	public String getTipSuperf() {
		return tipSuperf;
	}

	/**
	 * @return the tipSuport
	 */
	public String getTipSuport() {
		return tipSuport;
	}

	/**
	 * @return the cobertaEscocell
	 */
	public String getCobertaEscocell() {
		return cobertaEscocell;
	}

	/**
	 * @return the midaEscocell
	 */
	public String getMidaEscocell() {
		return midaEscocell;
	}

	/**
	 * @return the voraEscocell
	 */
	public String getVoraEscocell() {
		return voraEscocell;
	}

}
