package com.abc.ecommerce.service;

import com.abc.ecommerce.bo.TrackingRequest;
import com.abc.ecommerce.dto.TrackingResponseDTO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class TrackingService {

    @Autowired
    private RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final Map<String, String> countryMap = new HashMap<>();

    static {
        // Initialize the map with country code and name pairs
        countryMap.put("AF", "Afghanistan");
        countryMap.put("AL", "Albania");
        countryMap.put("DZ", "Algeria");
        countryMap.put("AD", "Andorra");
        countryMap.put("AO", "Angola");
        countryMap.put("AG", "Antigua and Barbuda");
        countryMap.put("AR", "Argentina");
        countryMap.put("AM", "Armenia");
        countryMap.put("AW", "Aruba");
        countryMap.put("AU", "Australia");
        countryMap.put("CX", "Australia - Christmas Island");
        countryMap.put("CC", "Australia - Cocos Islands");
        countryMap.put("NF", "Australia - Norfolk Island");
        countryMap.put("AT", "Austria");
        countryMap.put("AZ", "Azerbaijan");
        countryMap.put("BS", "Bahamas");
        countryMap.put("BH", "Bahrain");
        countryMap.put("BD", "Bangladesh");
        countryMap.put("BB", "Barbados");
        countryMap.put("BY", "Belarus");
        countryMap.put("BE", "Belgium");
        countryMap.put("BZ", "Belize");
        countryMap.put("BJ", "Benin");
        countryMap.put("BT", "Bhutan");
        countryMap.put("BO", "Bolivia");
        countryMap.put("BA", "Bosnia and Herzegovina");
        countryMap.put("BW", "Botswana");
        countryMap.put("BR", "Brazil");
        countryMap.put("BN", "Brunei");
        countryMap.put("BG", "Bulgaria");
        countryMap.put("BF", "Burkina Faso");
        countryMap.put("BI", "Burundi");
        countryMap.put("KH", "Cambodia");
        countryMap.put("CM", "Cameroon");
        countryMap.put("CA", "Canada");
        countryMap.put("CV", "Cape Verde");
        countryMap.put("CF", "Central African Republic");
        countryMap.put("TD", "Chad");
        countryMap.put("CL", "Chile");
        countryMap.put("CN", "China");
        countryMap.put("CO", "Colombia");
        countryMap.put("KM", "Comoros");
        countryMap.put("CG", "Congo Republic");
        countryMap.put("CR", "Costa Rica");
        countryMap.put("CI", "Cote d Ivoire");
        countryMap.put("HR", "Croatia");
        countryMap.put("CU", "Cuba");
        countryMap.put("CW", "Curacao");
        countryMap.put("CY", "Cyprus");
        countryMap.put("CZ", "Czech Republic");
        countryMap.put("CD", "Democratic Republic of Congo");
        countryMap.put("DK", "Denmark");
        countryMap.put("FO", "Denmark - Faroe Islands");
        countryMap.put("GL", "Denmark - Greenland");
        countryMap.put("DJ", "Djibouti");
        countryMap.put("DM", "Dominica");
        countryMap.put("DO", "Dominican Republic");
        countryMap.put("EC", "Ecuador");
        countryMap.put("EG", "Egypt");
        countryMap.put("SV", "El Salvador");
        countryMap.put("GQ", "Equatorial Guinea");
        countryMap.put("ER", "Eritrea");
        countryMap.put("EE", "Estonia");
        countryMap.put("ET", "Ethiopia");
        countryMap.put("FJ", "Fiji");
        countryMap.put("FI", "Finland");
        countryMap.put("AX", "Finland - Aland Islands");
        countryMap.put("FR", "France");
        countryMap.put("GF", "France - French Guiana");
        countryMap.put("PF", "France - French Polynesia");
        countryMap.put("GP", "France - Guadeloupe");
        countryMap.put("MQ", "France - Martinique");
        countryMap.put("YT", "France - Mayotte");
        countryMap.put("NC", "France - New Caledonia");
        countryMap.put("RE", "France - Reunion");
        countryMap.put("BL", "France - Saint Barthelemy");
        countryMap.put("MF", "France - Saint Martin");
        countryMap.put("PM", "France - St. Pierre and Miquelon");
        countryMap.put("WF", "France - Wallis and Futuna Islands");
        countryMap.put("GA", "Gabon");
        countryMap.put("GM", "Gambia");
        countryMap.put("GE", "Georgia");
        countryMap.put("DE", "Germany");
        countryMap.put("GH", "Ghana");
        countryMap.put("GR", "Greece");
        countryMap.put("GD", "Grenada");
        countryMap.put("GT", "Guatemala");
        countryMap.put("GN", "Guinea");
        countryMap.put("GW", "Guinea-Bissau");
        countryMap.put("GY", "Guyana");
        countryMap.put("HT", "Haiti");
        countryMap.put("HN", "Honduras");
        countryMap.put("HK", "Hong Kong");
        countryMap.put("HU", "Hungary");
        countryMap.put("IS", "Iceland");
        countryMap.put("IN", "India");
        countryMap.put("ID", "Indonesia");
        countryMap.put("IR", "Iran");
        countryMap.put("IQ", "Iraq");
        countryMap.put("IE", "Ireland");
        countryMap.put("IL", "Israel");
        countryMap.put("IT", "Italy");
        countryMap.put("JM", "Jamaica");
        countryMap.put("JP", "Japan");
        countryMap.put("JO", "Jordan");
        countryMap.put("KZ", "Kazakhstan");
        countryMap.put("KE", "Kenya");
        countryMap.put("KI", "Kiribati");
        countryMap.put("KP", "Korea, North (Democratic People's Republic of Korea)");
        countryMap.put("KR", "Korea, South (Republic of Korea)");
        countryMap.put("XZ", "Kosovo");
        countryMap.put("KW", "Kuwait");
        countryMap.put("KG", "Kyrgyzstan");
        countryMap.put("LA", "Laos");
        countryMap.put("LV", "Latvia");
        countryMap.put("LB", "Lebanon");
        countryMap.put("LS", "Lesotho");
        countryMap.put("LR", "Liberia");
        countryMap.put("LY", "Libya");
        countryMap.put("LI", "Liechtenstein");
        countryMap.put("LT", "Lithuania");
        countryMap.put("LU", "Luxembourg");
        countryMap.put("MO", "Macao");
        countryMap.put("MK", "Macedonia");
        countryMap.put("MG", "Madagascar");
        countryMap.put("MW", "Malawi");
        countryMap.put("MY", "Malaysia");
    }

    public static final String POS_URL = "https://www-api.pos.com.my/api/price";

    public TrackingResponseDTO posLogisticRestCall(TrackingRequest trackingRequest) {
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<TrackingRequest> request = new HttpEntity<TrackingRequest>(trackingRequest,headers);
            logger.info("Performing the rest call to Tacking API ");
            TrackingResponseDTO responseDTO = new TrackingResponseDTO();

            String response = restTemplate.postForObject("https://seller.tracking.my/api/services",trackingRequest, String.class);
            if(response.startsWith("<!DOCTYPE html>")){
                responseDTO.setError("Something went wrong:-->"+response);
            }else{
                JSONObject jsonObject = new JSONObject(response);
                // Get the "data" array
                JSONArray dataArray = jsonObject.getJSONObject("services").getJSONArray("data");
                // Get the first element in the array
                JSONObject firstElement = dataArray.getJSONObject(0);
                // Get the "price" value
                int price = firstElement.getInt("price");
                double finalPrice = price/100;
                responseDTO.setPrice(finalPrice);
            }
            return responseDTO;
        }catch (HttpStatusCodeException e){
            logger.error("Error Response from POS API with status: {} and error: {} ", e.getStatusCode(), e.getResponseBodyAsString());
            logger.trace(e.getResponseBodyAsString());
            TrackingResponseDTO posResponse = new TrackingResponseDTO();
            posResponse.setError("Http Status Code: "+ e.getStatusCode()+" error: " +e.getResponseBodyAsString());
            return posResponse;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
