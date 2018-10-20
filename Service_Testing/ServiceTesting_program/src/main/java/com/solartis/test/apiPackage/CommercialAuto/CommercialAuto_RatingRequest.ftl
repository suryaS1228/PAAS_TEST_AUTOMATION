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
         <#list AddedPersonalInjuryProtectionDetail?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		</#list>
        }
        }
        ],
	  "PrivatePassenger": [
      {
        "PrivatePassengerDetail":
        {
         <#list PrivatePassengerDetail?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		</#list>
        }
        }
        ],
        
      "Truck": 
      [
      {
        "TruckDetail":
         {
         <#list TruckDetail?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		</#list>
         }
       }
       ],	
		
		 "ZoneRated": [
      {
        "ZoneRatedDetail": 
        {
         <#list ZoneRatedDetail?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		</#list>
        }
        }
        ],
        
         "PublicTransportation": [
      {
        "PublicTransportationDetail":
        {
         <#list PublicTransportationDetail?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		</#list>
        }
        }
        ],
		
	 "Special": [
      {
        "SpecialDetail":
        {
         <#list SpecialDetail?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		</#list>
        }
        }
        ],
        "Garage": [
      {
        "GarageDetail":
        {
         <#list GarageDetail?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
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