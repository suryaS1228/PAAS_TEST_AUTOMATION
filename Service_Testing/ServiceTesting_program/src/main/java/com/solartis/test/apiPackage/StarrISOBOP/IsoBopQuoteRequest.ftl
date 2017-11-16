{
  "Policy": {
  "AdditionalInsuredList": [
      <#list additionalInsuredtype as result>{
        "${result.atrib}":"${result.value}",
        "AdditionalInsuredDetail": [
          {
          <#list additionalInsureddetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
          </#list>
          }
        ]
      }<#if result?is_last><#else>,</#if>
      </#list>
    ],
  "Location": {
      "LocationDetail": [
        {        
        "Classification": {
            "ClassificationDetail": [
              {
              <#list classification as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
              </#list>
              }
            ]
          },
        <#list location as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
        }
        ]
      },
  <#list Policy as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  </#list>
   },
   "OwnerId": "30",
   "ServiceRequestDetail": {
   <#list servicedetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  </#list>
  },
  "EndClientUserUniqueSessionId": "Uniquesession"
}        

{
    "ClientUniqueRequestID": "1",
    "EndClientUserBrowserInformation": "Mozilla/...",
    "EndClientUserBrowserSessionId": "7Eadfad4444",
    "EndClientUserUniqueSessionId": "Uniquesession",
    "OwnerId": "24",
    "PaaSClientIPAddress": "125.25.25.2",
    "Policy": {
            "ChangesLimitedFungiCoverage": {
            "BOPLimitedFungiBacteriaCovBusnIncomeExtraExpenseCoverageNumDays": "",
            "Limit": "15000",
            "SeparatePremisesLocationsOption": "No"
        },
     "HiredAutoAndNonOwnedAutoLiability": {
     <#list HiredNonOwned as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  </#list>
  },
      "Location": [
       {
      "LocationDetail": {
          "Building": [
           {
            "BuildingDetail": {
               "Classification": [
                 {
                 "ClassificationDetail": {
                	<#list classification as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
              </#list>
              }
              }
              ],
               "DebrisRemovalAdditionalInsurance": {
                  "Limit": "10000"
                },
                <#list building as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
              </#list>
              }
              }
              ],
               "WaterBackUpAndSumpOverflow": {
            "BusinessIncomeExtraExpenseAnnualAggregateLimit": "5000",
            "CoveredPropertyAnnualAggregateLimit": "5000",
            "Limit": "5000"
          },
  		 <#list location as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  		</#list>
  		}
  		}
  		],
  		 <#list policy as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  		</#list>
    },
     "ServiceRequestDetail": {
     <#list servicedetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  		</#list>
  	}