<#assign TravelerDOBarray=[]><#list TravelerDOB as x><#assign TravelerDOBarray=TravelerDOBarray+[x.value]></#list>
<#assign TravelCostarray=[]><#list TravelCost as x><#assign TravelCostarray=TravelCostarray+[x.value]></#list>
<#assign FirstNamearray=[]><#list FirstName as x><#assign FirstNamearray=FirstNamearray+[x.value]></#list>
<#assign LastNamearray=[]><#list LastName as x><#assign LastNamearray=LastNamearray+[x.value]></#list>
<#assign AddressLine1array=[]><#list AddressLine1 as x><#assign AddressLine1array=AddressLine1array+[x.value]></#list>
<#assign AddressLine2array=[]><#list AddressLine2 as x><#assign AddressLine2array=AddressLine2array+[x.value]></#list>
<#assign Cityarray=[]><#list City as x><#assign Cityarray=Cityarray+[x.value]></#list>
<#assign Statearray=[]><#list State as x><#assign Statearray=Statearray+[x.value]></#list>
<#assign StateCodearray=[]><#list StateCode as x><#assign StateCodearray=StateCodearray+[x.value]></#list>
<#assign Countryarray=[]><#list Country as x><#assign Countryarray=Countryarray+[x.value]></#list>
<#assign Zipcodearray=[]><#list Zipcode as x><#assign Zipcodearray=Zipcodearray+[x.value]></#list>
<#assign Phonearray=[]><#list Phone as x><#assign Phonearray=Phonearray+[x.value]></#list>
<#assign Emailarray=[]><#list Email as x><#assign Emailarray=Emailarray+[x.value]></#list>
<#assign TravelerIndexarray=[]><#list TravelerIndex as x><#assign TravelerIndexarray=TravelerIndexarray+[x.value]></#list>
<#assign OFACLicenseNumberarray=[]><#list OFACLicenseNumber as x><#assign OFACLicenseNumberarray=OFACLicenseNumberarray+[x.value]></#list>
 <#assign i=0>
{
  "PolicyInformation": {
  	"TravelerList": [
       
  	<#list 1..numofTraveler[0] as result>
      {
      	"ProviderDetail": {
      	  "TravelerDOB": "${TravelerDOBarray[i]}",
          "TravelCost": "${TravelCostarray[i]}",
          "FirstName": "${FirstNamearray[i]}",
          "LastName": "${LastNamearray[i]}",
          "AddressLine1": "${AddressLine1array[i]}",
          "AddressLine2": "${AddressLine2array[i]}",
          "City": "${Cityarray[i]}",
          "State": "${Statearray[i]}",
          "StateCode": "${StateCodearray[i]}",
          "Country": "${Countryarray[i]}",
	  "Zipcode": "${Zipcodearray[i]}",
	  "Phone": "${Phonearray[i]}",
	  "Email": "${Emailarray[i]}",
	  "TravelerIndex": "${TravelerIndexarray[i]}",
	  "OFACLicenseNumber": "${OFACLicenseNumberarray[i]}",
      	}
      }<#assign i=i+1>
         </#list>
        "PaymentInformation": 
              {
              <#list PaymentInformation as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
              </#list>
              },
     
     <#list PolicyInformation as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list> 
    },
  
  "ServiceRequestDetail": {
  <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
  },
  "EndClientUserBrowserInformation": "192.168.5.12",
  "PaaSClientIPAddress": "192.168.5.12",
  "EndClientUserUniqueSessionId": "1",
  "EndClientUserBrowserSessionId": "12",
  "ClientUniqueRequestID": "12587"
}