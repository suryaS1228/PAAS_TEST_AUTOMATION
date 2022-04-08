<#assign numoftravelers=[]><#list No_Of_Travellers as x><#assign numoftravelers=numoftravelers+[x.value]></#list>
{
 "EndClientUserUniqueSessionId": "1",
 "EndClientUserBrowserInformation": "192.168.5.12",
 "PaaSClientIPAddress": "192.168.5.12",
 "EndClientUserBrowserSessionId": "12",
 "ClientUniqueRequestID": "12587",
 "PolicyInformation": 
  {
    <#list PolicyInformation as result>
    "${result.atrib}":"${result.value}",
    </#list>
  
  "PaymentInformation": {
    <#list PaymentInformation as result>
    "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
    </#list>
   },
    "TravelerList":<#assign i=1> [
    <#list 1..numoftravelers[0] as result>
     {
     <#assign TravelerListS = "TravelerList_"+i>
     <#list TravelerListS?eval as result>	  
	 "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>	  
	 </#list>
     }<#if result?is_last><#else>,</#if><#assign i=i+1>
     </#list>
    ]
   },
   "ServiceRequestDetail": {
    <#list ServiceRequestDetail as result>
    "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
    </#list>
   }
 }