<#assign AdditionalInsuredActionArray=[]><#list AdditionalInsuredAction as x><#assign AdditionalInsuredActionArray=AdditionalInsuredActionArray+[x.value]></#list>
<#assign AdditionalInsuredNumberArray=[]><#list AdditionalInsuredNumber as x><#assign AdditionalInsuredNumberArray=AdditionalInsuredNumberArray+[x.value]></#list>
<#assign AdditionalInsuredTypeArray=[]><#list AdditionalInsuredType as x><#assign AdditionalInsuredTypeArray=AdditionalInsuredTypeArray+[x.value]></#list>
<#assign NoOfAdditionalInsuredArray=[]><#list NoOfAdditionalInsured as x><#assign NoOfAdditionalInsuredArray=NoOfAdditionalInsuredArray+[x.value]></#list>
{
  "ServiceRequestDetail": {
  <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  </#list>
	},
  "RepeatedAttributeListDetail": [
      {
      "Key": "ISO::BOP::AdditionalInsuredList",
      "AttributeDetailList": [
        <#assign i=0>
        <#if NoOfAdditionalInsuredArray[0]==0><#else>
        <#list 1..NoOfAdditionalInsuredArray[0] as result>
        {
          "RepeatedAttributeDetail": [
            {
              "Key": "ISO::BOP::AdditionalInsuredDetails",
              "AttributeDetailList": [
                {
                  "AttributeDetail": [
                 	{
                      "Value": "${AdditionalInsuredActionArray[i]}",
                      "Key": "ISO::BOP::AdditionalInsured::Action"
                    },
                    {
                      "Value": "${AdditionalInsuredNumberArray[i]}",
                      "Key": "ISO::BOP::AdditionalInsured::Number"
                    },
                    {
                      "Value": "Apartment and Office",
                      "Key": "ISO::BOP::TypeRisk"
                    },
                    {
                      "Value": "Product",
                      "Key": "ISO::BOP::AdditionalInsured::YourProducts"
                    },
                    {
                      "Value": "Desc",
                      "Key": "ISO::BOP::AdditionalInsured::DescOfLeasedEquipment"
                    },
                    {
                      "Value": "FirstName",
                      "Key": "ISO::BOP::AdditionalInsured::Name"
                    },
                    {
                      "Value": "DescOfProp",
                      "Key": "ISO::BOP::AdditionalInsured::DescOfProperty"
                    },
                    {
                      "Value": "123",
                      "Key": "ISO::BOP::AdditionalInsured::AddressLine1"
                    },
                    {
                      "Value": "aaa",
                      "Key": "ISO::BOP::AdditionalInsured::AddressLine2"
                    },
                    {
                      "Value": "54321",
                      "Key": "ISO::BOP::AdditionalInsured::ZipCode"
                    },
                    {
                      "Value": "aaa",
                      "Key": "ISO::BOP::AdditionalInsured::City"
                    },
                    {
                      "Value": "aaa",
                      "Key": "ISO::BOP::AdditionalInsured::County"
                    },
                    {
                      "Value": "AZ",
                      "Key": "ISO::BOP::AdditionalInsured::State"
                    },
                    {
                      "Value": "LocAndDesc",
                      "Key": "ISO::BOP::AdditionalInsured::LocAndDescOfCompOperations"
                    },
                    {
                      "Value": "B",
                      "Key": "ISO::BOP::COI::Description"
                    },
                    {
                      "Value": "B",
                      "Key": "ISO::BOP::LossPayableApplicableClause"
                    },
                    {
                      "Value": "71837",
                      "Key": "ISO::BOP::AdditionalInsured::PassBuilding::ZipCode"
                    }
                  ]
                }
              ]
            }
          ],
          "AttributeDetail": [
            {
              "Value": "${AdditionalInsuredTypeArray[i]}",
              "Key": "ISO::BOP::AdditionalInsuredType"
            }
          ]
        }<#if result?is_last><#else>,</#if><#assign i=i+1>
           </#list>
           </#if>
      ]
    },
    {
      "Key": "ISO::BOP::ClaimsList",
      "AttributeDetailList": [
        {
          "AttributeDetail": [
            {
              "Value": "10000",
              "Key": "ISO::BOP::AmountPaidForClaim"
            },
            {
              "Value": "2012-01-16",
              "Key": "ISO::BOP::ClaimOccurenceDate"
            },
            {
              "Value": "N",
              "Key": "ISO::BOP::IsClaimClosed"
            },
            {
              "Value": "test",
              "Key": "ISO::BOP::LossCause"
            }
          ]
        },
        {
          "AttributeDetail": [
            {
              "Value": "10000",
              "Key": "ISO::BOP::AmountPaidForClaim"
            },
            {
              "Value": "2012-01-16",
              "Key": "ISO::BOP::ClaimOccurenceDate"
            },
            {
              "Value": "N",
              "Key": "ISO::BOP::IsClaimClosed"
            },
            {
              "Value": "test",
              "Key": "ISO::BOP::LossCause"
            }
          ]
        }
      ]
    },
    {
      "Key": "ISO::BOP::Loc::Bldg::MortgageholderList",
      "AttributeDetailList": [
        {
          "AttributeDetail": [
            {
              "Value": "MortgageholderName1",
              "Key": "ISO::BOP::Loc::Bldg::MortgageholderName"
            },
            {
              "Value": "123.ftsf'gcdgs & hchsdch",
              "Key": "ISO::BOP::Loc::Bldg::Addressline1"
            },
            {
              "Value": "gfsdg jbdhsabd hbsdhbh",
              "Key": "ISO::BOP::Loc::Bldg::Addressline2"
            },
            {
              "Value": "Abercrombie",
              "Key": "ISO::BOP::Loc::Bldg::City"
            },
            {
              "Value": "Richland",
              "Key": "ISO::BOP::Loc::Bldg::County"
            },
            {
              "Value": "ND",
              "Key": "ISO::BOP::Loc::Bldg::State"
            },
            {
              "Value": "58001",
              "Key": "ISO::BOP::Loc::Bldg::ZipCode"
            }
          ]
        },
        {
          "AttributeDetail": [
            {
              "Value": "MortgageholderName2",
              "Key": "ISO::BOP::Loc::Bldg::MortgageholderName"
            },
            {
              "Value": "123.ftsf'gcdgs & hchsdch",
              "Key": "ISO::BOP::Loc::Bldg::Addressline1"
            },
            {
              "Value": "gfsdg jbdhsabd hbsdhbh",
              "Key": "ISO::BOP::Loc::Bldg::Addressline2"
            },
            {
              "Value": "Abercrombie",
              "Key": "ISO::BOP::Loc::Bldg::City"
            },
            {
              "Value": "Richland",
              "Key": "ISO::BOP::Loc::Bldg::County"
            },
            {
              "Value": "ND",
              "Key": "ISO::BOP::Loc::Bldg::State"
            },
            {
              "Value": "58001",
              "Key": "ISO::BOP::Loc::Bldg::ZipCode"
            }
          ]
        }
      ]
    },
    {
      "Key": "ISO::BOP::Loc::RiskApplicableSubQuestionList",
      "AttributeDetailList": [
        {
          "AttributeDetail": [
            {
              "Value": "You manufacture the product that you sell",
              "Key": "ISO::BOP::Loc::RiskApplicableSubQuestionDescription"
            },
            {
              "Value": "1",
              "Key": "ISO::BOP::Loc::RiskApplicableSubQuestionDisplayOrder"
            }
          ]
        },
        {
          "AttributeDetail": [
            {
              "Value": "You own any brands for the products you are selling",
              "Key": "ISO::BOP::Loc::RiskApplicableSubQuestionDescription"
            },
            {
              "Value": "2",
              "Key": "ISO::BOP::Loc::RiskApplicableSubQuestionDisplayOrder"
            }
          ]
        },
        {
          "AttributeDetail": [
            {
              "Value": "Location is open 24 hours",
              "Key": "ISO::BOP::Loc::RiskApplicableSubQuestionDescription"
            },
            {
              "Value": "4",
              "Key": "ISO::BOP::Loc::RiskApplicableSubQuestionDisplayOrder"
            }
          ]
        },
        {
          "AttributeDetail": [
            {
              "Value": "Location is over 35,000 sq. ft.",
              "Key": "ISO::BOP::Loc::RiskApplicableSubQuestionDescription"
            },
            {
              "Value": "5",
              "Key": "ISO::BOP::Loc::RiskApplicableSubQuestionDisplayOrder"
            }
          ]
        },
        {
          "AttributeDetail": [
            {
              "Value": "Insured or any partner(s) in the business ever been convicted of a felony",
              "Key": "ISO::BOP::Loc::RiskApplicableSubQuestionDescription"
            },
            {
              "Value": "6",
              "Key": "ISO::BOP::Loc::RiskApplicableSubQuestionDisplayOrder"
            }
          ]
        },
        {
          "AttributeDetail": [
            {
              "Value": "Insured or any partner(s) ever declared bankruptcy",
              "Key": "ISO::BOP::Loc::RiskApplicableSubQuestionDescription"
            },
            {
              "Value": "3",
              "Key": "ISO::BOP::Loc::RiskApplicableSubQuestionDisplayOrder"
            }
          ]
        }
      ]
    },
    {
      "Key": "ISO::BOP::Loc::Bldg::Classification::UWQuestionList",
      "AttributeDetailList": [
        {
          "AttributeDetail": [
            {
              "Value": "At any of your locations that you operate from do you rent and/ or sell your product to customers?",
              "Key": "ISO::BOP::Loc::Bldg::Classification::UWQuestion"
            },
            {
              "Value": "34",
              "Key": "ISO::BOP::Loc::Bldg::Classification::UWQuestionId"
            },
            {
              "Value": "Yes",
              "Key": "ISO::BOP::Loc::Bldg::Classification::UWAnswer"
            }
          ]
        },
        {
          "AttributeDetail": [
            {
              "Value": "At any location that you operate from, do you sell any used parts and/ or rent any of your products to customers?",
              "Key": "ISO::BOP::Loc::Bldg::Classification::UWQuestion"
            },
            {
              "Value": "46",
              "Key": "ISO::BOP::Loc::Bldg::Classification::UWQuestionId"
            },
            {
              "Value": "No",
              "Key": "ISO::BOP::Loc::Bldg::Classification::UWAnswer"
            }
          ]
        }
      ]
    }
  ],
  "AttributeListDetail": {
    "AttributeDetail": [
	    <#list QuoteInformation as result>
	    {
	    	"Key" : "${result.atrib}",
	    	"Value" : "${result.value}"
	    }<#if result?is_last><#else>,</#if>
	  </#list>
 	 ]
  }
}