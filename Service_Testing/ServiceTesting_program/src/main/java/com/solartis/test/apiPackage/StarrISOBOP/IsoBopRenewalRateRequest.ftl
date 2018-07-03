<#assign NumofAIArray=[]><#list NumofAI as x><#assign NumofAIArray=NumofAIArray+[x.value]></#list>
<#assign i=1>
{
  "ServiceRequestDetail": {
  <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  </#list>
  },
  "OwnerId": "24",
  "EndClientUserUniqueSessionId": "Uniquesession",
  "Policy": {
  <#list Policy as result>"${result.atrib}":"${result.value}",
  </#list>
  "Location": [
      {
        "LocationDetail": {
      <#list LocationDetail as result>"${result.atrib}":"${result.value}",
  		</#list>
      "Building": [
            {
              "BuildingDetail": {
              <#list BuildingDetail as result>"${result.atrib}":"${result.value}",
  				</#list>
              "Classification": [
                  {
                    "ClassificationDetail": {
                    <#list ClassificationDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  					</#list>
                    }
                  }
                ]
              }
            }
          ]
        }
      }
    ],
    "AdditionalInsuredList": [
    <#list 1..NumofAIArray[0] as result>
  	<#assign AdditionalInsuredType="AdditionInsured_Type"+i>
    
    {
    <#list AdditionalInsuredType?eval as result>
    "${result.atrib}":"${result.value}",
    </#list>
    "AdditionalInsuredDetail": [
          {
          <#list AdditionalInsuredDetail as result>
          <#if result.atrib=="AINumber">"${result.atrib}":"${result.value}${i}",<#else>
          "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
          </#if>
  		  </#list>
  		  }   	
  	   ]
    }
    <#if i=NumofAIArray[0]><#else>,</#if><#assign i=i+1>
      </#list>
    ],    
    "MortgageholderList": [
      {
        "MortgageholderName": "MortgageholderName1",
        "Addressline1": "123.ftsf'gcdgs & hchsdch",
        "Addressline2": "gfsdg jbdhsabd hbsdhbh",
        "City": "Abercrombie",
        "County": "Richland",
        "State": "ND",
        "ZipCode": "58001"
      },
      {
        "MortgageholderName": "MortgageholderName2",
        "Addressline1": "123.ftsf'gcdgs & hchsdch",
        "Addressline2": "gfsdg jbdhsabd hbsdhbh",
        "City": "Abercrombie",
        "County": "Richland",
        "State": "ND",
        "ZipCode": "58001"
      }
    ],
    "RiskApplicableSubQuestionList": [
      {
        "RiskApplicableSubQuestionDescription": "You manufacture the product that you sell",
        "RiskApplicableSubQuestionDisplayOrder": "1"
      },
      {
        "RiskApplicableSubQuestionDescription": "You own any brands for the products you are selling",
        "RiskApplicableSubQuestionDisplayOrder": "2"
      },
      {
        "RiskApplicableSubQuestionDescription": "Insured or any partner(s) ever declared bankruptcy",
        "RiskApplicableSubQuestionDisplayOrder": "3"
      },
      {
        "RiskApplicableSubQuestionDescription": "Location is open 24 hours",
        "RiskApplicableSubQuestionDisplayOrder": "4"
      },
      {
        "RiskApplicableSubQuestionDescription": "Location is over 35,000 sq. ft.",
        "RiskApplicableSubQuestionDisplayOrder": "5"
      },
      {
        "RiskApplicableSubQuestionDescription": "Insured or any partner(s) in the business ever been convicted of a felony",
        "RiskApplicableSubQuestionDisplayOrder": "6"
      }
    ],
    "UWQuestionList": [
      {
        "UWQuestion": "At any of your locations that you operate from do you rent and/ or sell your product to customers?",
        "UWQuestionId": "34",
        "UWAnswer": "Yes"
      },
      {
        "UWQuestion": "At any location that you operate from, do you sell any used parts and/ or rent any of your products to customers?",
        "UWQuestionId": "46",
        "UWAnswer": "No"
      }
    ],
    "ClaimsList": [
      {
        "AmountPaidForClaim": "10000.0000",
        "ClaimOccurenceDate": "2012-01-16",
        "IsClaimClosed": "N",
        "LossCause": "test"
      },
      {
        "AmountPaidForClaim": "10000.0000",
        "ClaimOccurenceDate": "2012-01-16",
        "IsClaimClosed": "N",
        "LossCause": "test"
      }
    ]
  }
}      
            