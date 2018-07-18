{
  "Policy": {
   <#list Policy as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
   </#list> 
  "Location": [
      {
        "LocationDetail": {
          "Building": [
            {
              "BuildingDetail": {
                "Classification": [
                  {
                  "ClassificationDetail":{
                  <#list Classification as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
                  </#list>
                  }
                  }
                ],
                "BuildingId": "1"
              }
            }
          ],
          "LocationId": "1"
        }
      }
    ]
  },
  "OwnerId": "27",
  "ServiceRequestDetail": {	
   <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  </#list>
  },
  "EndClientUserUniqueSessionId": "Uniquesession"
}