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
  	
 {
    "AttributeListDetail": {
        "AttributeDetail": [
        <#list policy as result>{
          "Key": "${result.atrib}",
          "Value":"${result.value}"
          }<#if result?is_last><#else>,</#if>
  		</#list>
  		]
  	},
  	    "RepeatedAttributeListDetail": [
        {
            "AttributeDetailList": [
                {
                    "AttributeDetail": [
                        {
                            "Key": "ISO::BOP::AmountPaidForClaim",
                            "Value": "10000"
                        },
                        {
                            "Key": "ISO::BOP::ClaimOccurenceDate",
                            "Value": "2012-01-16"
                        },
                        {
                            "Key": "ISO::BOP::IsClaimClosed",
                            "Value": "N"
                        },
                        {
                            "Key": "ISO::BOP::LossCause",
                            "Value": "test"
                        }
                    ]
                },
                {
                    "AttributeDetail": [
                        {
                            "Key": "ISO::BOP::AmountPaidForClaim",
                            "Value": "10000"
                        },
                        {
                            "Key": "ISO::BOP::ClaimOccurenceDate",
                            "Value": "2012-01-16"
                        },
                        {
                            "Key": "ISO::BOP::IsClaimClosed",
                            "Value": "N"
                        },
                        {
                            "Key": "ISO::BOP::LossCause",
                            "Value": "test"
                        }
                    ]
                }
            ],
            "Key": "ISO::BOP::ClaimsList"
        },
        {
            "AttributeDetailList": [
            
              {
                "AttributeDetail": [
                <#list policy as result>{
          "Key": "${result.atrib}",
          "Value":"${result.value}"
          }<#if result?is_last><#else>,</#if>
  		</#list>
  		]
  	},
  	
  	