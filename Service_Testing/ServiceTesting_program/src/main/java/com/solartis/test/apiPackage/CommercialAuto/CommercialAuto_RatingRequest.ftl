{
 <#list Common as result>"${result.atrib}":"${result.value}",
 </#list>
  "Policy": {
  		<#list Policy as result>"${result.atrib}":"${result.value}",
		</#list>
		
		"AddedPersonalInjuryProtection": 
		[
        {
        "AddedPersonalInjuryProtectionDetail": 
        {
         <#list AddedPersonalInjuryProtectionDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		</#list>
        }
        }
        ],
	  "PrivatePassenger": [
      {
        "PrivatePassengerDetail":
        {
         <#list PrivatePassengerDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		</#list>
        }
        }
        ],
        
      "Truck": 
      [
      {
        "TruckDetail":
         {
         <#list TruckDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		</#list>
         }
       }
       ],	
		
		 "ZoneRated": [
      {
        "ZoneRatedDetail": 
        {
         <#list ZoneRated as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		</#list>
        }
        }
        ],
        
         "PublicTransportation": [
      {
        "PublicTransportationDetail":
        {
         <#list PublicTransportationDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		</#list>
        }
        }
        ],
		
	 "Special": [
      {
        "SpecialDetail":
        {
         <#list SpecialDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		</#list>
        }
        }
        ],
        "Garage": [
      {
        "GarageDetail":
        {
         <#list GarageDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		</#list>
        }
        }
        ]
        },
  "ServiceRequestDetail": {
  <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		</#list>
  }
}