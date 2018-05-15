<#assign numofTrucks=[]><#list numofTruck as x><#assign numofTrucks=numofTrucks+[x.value]></#list>
<#assign numofPPassengers=[]><#list numofPPassenger as x><#assign numofPPassengers=numofPPassengers+[x.value]></#list>
{
  "EndClientUserUniqueSessionId": "Uniquesession",
  "OwnerId": "35",
  "Policy": {
  		<#list Policy as result>"${result.atrib}":"${result.value}",
		</#list>
      "Truck": [<#assign i=1>
      <#list 1..numofTrucks[0] as result><#assign TruckDetails="TruckDetail">
      {
           "TruckDetail": {
           <#list TruckDetails?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		</#list>
           }
      }<#if result?is_last><#else>,</#if><#assign i=i+1>
      </#list>
    ],
    "PrivatePassenger": [<#assign i=1>
      <#list 1..numofPPassengers[0] as result><#assign PrivatePassengerDetails="PrivatePassengerDetail">
      {
        "PrivatePassengerDetail": {
        <#list PrivatePassengerDetails?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		</#list>
        }
      }<#if result?is_last><#else>,</#if><#assign i=i+1>
      </#list>
    ],
    "AdditionalInsuredList": [
      {
      
      "AdditionalInsuredDetail": [
          {
          <#list AdditionalInsuredDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		</#list>
          }
        ]
      }
    ],
    "HiredAuto": [
      {
        "HiredAutoDetail": {
        <#list HiredAutoDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		</#list>
        }
      }
    ],
    "NonOwnedAuto": [
      {
        "NonOwnedAutoDetail": {
        <#list NonOwnedAutoDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
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