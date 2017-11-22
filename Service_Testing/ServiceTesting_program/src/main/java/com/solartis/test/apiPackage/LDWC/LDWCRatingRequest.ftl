<#function notEmpty atrib value>
  <#return !(value)?? || !(atrib)?? || atrib !="" || !atrib?? || value!="" || !atrib?has_content || !value?has_content|| !value??/>  
 </#function> 
<ACORD>
	<SignonRq>
		<SignonPswd>
			<CustId>
				<CustLoginId>starrcscadmin</CustLoginId>
			</CustId>
			<CustPswd>
				<Pswd>sKVSbX7wj8UYYAaar4amxQ==</Pswd>
			</CustPswd>
		</SignonPswd>
		<ClientDt>2012-09-14T11:03:46.073</ClientDt>
		<ClientApp>
			<Org>Starr</Org>
			<Name>LDCC</Name>
			<Version>1.00</Version>
		</ClientApp>
	</SignonRq>
	<InsuranceSvcRq>
	<#list Insurance as result><${result.atrib}>${result.value}</${result.atrib}>
    </#list>
    <LargeDeductibleCreditCalculationRq>
		<MsgStatus>
	<#list MsgStatus as result><${result.atrib}>${result.value}</${result.atrib}>
    </#list>
		</MsgStatus>
	<#list LargeDeductibleCredit as result><${result.atrib}>${result.value}</${result.atrib}>
    </#list>
    	<Producer>
				<ItemIdInfo>
					<AgencyId>1800001</AgencyId>
				</ItemIdInfo>
				<ProducerInfo>
					<ContractNumber>starrunderwriter</ContractNumber>
				</ProducerInfo>
			</Producer>
			<InsuredOrPrincipal>
				<GeneralPartyInfo>
					<NameInfo>
						<CommlName>
						<#list GeneralPartyInfo as result><${result.atrib}>${result.value}</${result.atrib}>
    </#list>
    					</CommlName>
					</NameInfo>
				</GeneralPartyInfo>
				<InsuredOrPrincipalInfo>
					<InsuredOrPrincipalRoleCd>NI</InsuredOrPrincipalRoleCd>
				</InsuredOrPrincipalInfo>
			</InsuredOrPrincipal>
			<CommlPolicy>
			<#list CommlPolicy as result><${result.atrib}>${result.value}</${result.atrib}>
    </#list>
    		<ContractTerm>
			<#list ContractTerm as result><${result.atrib}>${result.value}</${result.atrib}>
    </#list>
    		</ContractTerm>
    		<CurrentTermAmt>
			<#list CurrentTermAmt as result><${result.atrib}>${result.value}</${result.atrib}>
    </#list>
				</CurrentTermAmt>
			<CommlCoverage>
					<CoverageCd>WC</CoverageCd>
					<Deductible>
						<DeductibleTypeCd>00</DeductibleTypeCd>
						<DeductibleAppliesToCd>Amount</DeductibleAppliesToCd>
						<FormatCurrency>
						<#list FormatCurrency as result><${result.atrib}>${result.value}</${result.atrib}>
    </#list>
    					</FormatCurrency>
					</Deductible>
					<Deductible>
						<DeductibleTypeCd>00</DeductibleTypeCd>
						<DeductibleAppliesToCd>Aggregate</DeductibleAppliesToCd>
						<FormatCurrency>
						<#list FormatCurrency1 as result><${result.atrib}>${result.value}</${result.atrib}>
    </#list>
    					</FormatCurrency>
					</Deductible>
				</CommlCoverage>
			</CommlPolicy>
			<#--State 1====================================================================================================================================================================-->
			<#if State1?exists><#list State1 as result><#if notEmpty(result.atrib,result.value)>
			<WorkCompLineBusiness>
				<LOBCd>WC</LOBCd>
				<WorkCompRateState>
					<${result.atrib}>${result.value}</${result.atrib}>
					<CreditOrSurcharge>
						<CreditSurchargeCd>EMOD</CreditSurchargeCd>
						<NumericValue>
							<#list State1EMOD as result><${result.atrib}>${result.value}</${result.atrib}></#list>
							</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
							<#list State1LD as result><${result.atrib}>${result.value}</${result.atrib}></#list>
					<NumericValue>
							<#list State1LDValue as result><${result.atrib}>${result.value}</${result.atrib}></#list>
					</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
						<CreditSurchargeCd>MRAP</CreditSurchargeCd>
						<NumericValue>
							<#list State1MRAP as result><${result.atrib}>${result.value}</${result.atrib}></#list>
						</NumericValue>
					</CreditOrSurcharge>
					<#if State1Class1?exists><WorkCompRateClass><#list State1Class1 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State1Class2?exists><WorkCompRateClass><#list State1Class2 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State1Class3?exists><WorkCompRateClass><#list State1Class3 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State1Class4?exists><WorkCompRateClass><#list State1Class4 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State1Class5?exists><WorkCompRateClass><#list State1Class5 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
				</WorkCompRateState>
			</WorkCompLineBusiness>
			</#if>
			</#list></#if>
			
			<#--State 2====================================================================================================================================================================-->
			<#if State2?exists><#list State2 as result><#if notEmpty(result.atrib,result.value)>
			<WorkCompLineBusiness>
				<LOBCd>WC</LOBCd>
				<WorkCompRateState>
					<${result.atrib}>${result.value}</${result.atrib}>
					<CreditOrSurcharge>
						<CreditSurchargeCd>EMOD</CreditSurchargeCd>
						<NumericValue>
							<#list State2EMOD as result><${result.atrib}>${result.value}</${result.atrib}></#list>
							</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
							<#list State2LD as result><${result.atrib}>${result.value}</${result.atrib}></#list>
					<NumericValue>
							<#list State2LDValue as result><${result.atrib}>${result.value}</${result.atrib}></#list>
					</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
						<CreditSurchargeCd>MRAP</CreditSurchargeCd>
						<NumericValue>
							<#list State2MRAP as result><${result.atrib}>${result.value}</${result.atrib}></#list>
						</NumericValue>
					</CreditOrSurcharge>
					<#if State2Class1?exists><WorkCompRateClass><#list State2Class1 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State2Class2?exists><WorkCompRateClass><#list State2Class2 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State2Class3?exists><WorkCompRateClass><#list State2Class3 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State2Class4?exists><WorkCompRateClass><#list State2Class4 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State2Class5?exists><WorkCompRateClass><#list State2Class5 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
				</WorkCompRateState>
			</WorkCompLineBusiness>
			</#if>
			</#list></#if>
			<#--State 3====================================================================================================================================================================-->
			<#if State3?exists><#list State3 as result><#if notEmpty(result.atrib,result.value)>
			<WorkCompLineBusiness>
				<LOBCd>WC</LOBCd>
				<WorkCompRateState>
					<${result.atrib}>${result.value}</${result.atrib}>
					<CreditOrSurcharge>
						<CreditSurchargeCd>EMOD</CreditSurchargeCd>
						<NumericValue>
							<#list State3EMOD as result><${result.atrib}>${result.value}</${result.atrib}></#list>
							</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
							<#list State3LD as result><${result.atrib}>${result.value}</${result.atrib}></#list>
					<NumericValue>
							<#list State3LDValue as result><${result.atrib}>${result.value}</${result.atrib}></#list>
					</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
						<CreditSurchargeCd>MRAP</CreditSurchargeCd>
						<NumericValue>
							<#list State3MRAP as result><${result.atrib}>${result.value}</${result.atrib}></#list>
						</NumericValue>
					</CreditOrSurcharge>
					<#if State3Class1?exists><WorkCompRateClass><#list State3Class1 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State3Class2?exists><WorkCompRateClass><#list State3Class2 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State3Class3?exists><WorkCompRateClass><#list State3Class3 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State3Class4?exists><WorkCompRateClass><#list State3Class4 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State3Class5?exists><WorkCompRateClass><#list State3Class5 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
				</WorkCompRateState>
			</WorkCompLineBusiness>
			</#if>
			</#list></#if>
			<#--State 4====================================================================================================================================================================-->
			<#if State4?exists><#list State4 as result><#if notEmpty(result.atrib,result.value)>
			<WorkCompLineBusiness>
				<LOBCd>WC</LOBCd>
				<WorkCompRateState>
					<${result.atrib}>${result.value}</${result.atrib}>
					<CreditOrSurcharge>
						<CreditSurchargeCd>EMOD</CreditSurchargeCd>
						<NumericValue>
							<#list State4EMOD as result><${result.atrib}>${result.value}</${result.atrib}></#list>
							</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
							<#list State4LD as result><${result.atrib}>${result.value}</${result.atrib}></#list>
					<NumericValue>
							<#list State4LDValue as result><${result.atrib}>${result.value}</${result.atrib}></#list>
					</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
						<CreditSurchargeCd>MRAP</CreditSurchargeCd>
						<NumericValue>
							<#list State4MRAP as result><${result.atrib}>${result.value}</${result.atrib}></#list>
						</NumericValue>
					</CreditOrSurcharge>
					<#if State4Class1?exists><WorkCompRateClass><#list State4Class1 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State4Class2?exists><WorkCompRateClass><#list State4Class2 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State4Class3?exists><WorkCompRateClass><#list State4Class3 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State4Class4?exists><WorkCompRateClass><#list State4Class4 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State4Class5?exists><WorkCompRateClass><#list State4Class5 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
				</WorkCompRateState>
			</WorkCompLineBusiness>
			</#if>
			</#list></#if>
			<#--State 5====================================================================================================================================================================-->
			<#if State5?exists><#list State5 as result><#if notEmpty(result.atrib,result.value)>
			<WorkCompLineBusiness>
				<LOBCd>WC</LOBCd>
				<WorkCompRateState>
					<${result.atrib}>${result.value}</${result.atrib}>
					<CreditOrSurcharge>
						<CreditSurchargeCd>EMOD</CreditSurchargeCd>
						<NumericValue>
							<#list State5EMOD as result><${result.atrib}>${result.value}</${result.atrib}></#list>
							</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
							<#list State5LD as result><${result.atrib}>${result.value}</${result.atrib}></#list>
					<NumericValue>
							<#list State5LDValue as result><${result.atrib}>${result.value}</${result.atrib}></#list>
					</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
						<CreditSurchargeCd>MRAP</CreditSurchargeCd>
						<NumericValue>
							<#list State5MRAP as result><${result.atrib}>${result.value}</${result.atrib}></#list>
						</NumericValue>
					</CreditOrSurcharge>
					<#if State5Class1?exists><WorkCompRateClass><#list State5Class1 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State5Class2?exists><WorkCompRateClass><#list State5Class2 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State5Class3?exists><WorkCompRateClass><#list State5Class3 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State5Class4?exists><WorkCompRateClass><#list State5Class4 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State5Class5?exists><WorkCompRateClass><#list State5Class5 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
				</WorkCompRateState>
			</WorkCompLineBusiness>
			</#if>
			</#list></#if>
			<#--State 6====================================================================================================================================================================-->
			<#if State6?exists><#list State6 as result><#if notEmpty(result.atrib,result.value)>
			<WorkCompLineBusiness>
				<LOBCd>WC</LOBCd>
				<WorkCompRateState>
					<${result.atrib}>${result.value}</${result.atrib}>
					<CreditOrSurcharge>
						<CreditSurchargeCd>EMOD</CreditSurchargeCd>
						<NumericValue>
							<#list State6EMOD as result><${result.atrib}>${result.value}</${result.atrib}></#list>
							</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
							<#list State6LD as result><${result.atrib}>${result.value}</${result.atrib}></#list>
					<NumericValue>
							<#list State6LDValue as result><${result.atrib}>${result.value}</${result.atrib}></#list>
					</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
						<CreditSurchargeCd>MRAP</CreditSurchargeCd>
						<NumericValue>
							<#list State6MRAP as result><${result.atrib}>${result.value}</${result.atrib}></#list>
						</NumericValue>
					</CreditOrSurcharge>
					<#if State6Class1?exists><WorkCompRateClass><#list State6Class1 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State6Class2?exists><WorkCompRateClass><#list State6Class2 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State6Class3?exists><WorkCompRateClass><#list State6Class3 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State6Class4?exists><WorkCompRateClass><#list State6Class4 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State6Class5?exists><WorkCompRateClass><#list State6Class5 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
				</WorkCompRateState>
			</WorkCompLineBusiness>
			</#if>
			</#list></#if>
			<#--State 7====================================================================================================================================================================-->
			<#if State7?exists><#list State7 as result><#if notEmpty(result.atrib,result.value)>
			<WorkCompLineBusiness>
				<LOBCd>WC</LOBCd>
				<WorkCompRateState>
					<${result.atrib}>${result.value}</${result.atrib}>
					<CreditOrSurcharge>
						<CreditSurchargeCd>EMOD</CreditSurchargeCd>
						<NumericValue>
							<#list State7EMOD as result><${result.atrib}>${result.value}</${result.atrib}></#list>
							</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
							<#list State7LD as result><${result.atrib}>${result.value}</${result.atrib}></#list>
					<NumericValue>
							<#list State7LDValue as result><${result.atrib}>${result.value}</${result.atrib}></#list>
					</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
						<CreditSurchargeCd>MRAP</CreditSurchargeCd>
						<NumericValue>
							<#list State7MRAP as result><${result.atrib}>${result.value}</${result.atrib}></#list>
						</NumericValue>
					</CreditOrSurcharge>
					<#if State7Class1?exists><WorkCompRateClass><#list State7Class1 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State7Class2?exists><WorkCompRateClass><#list State7Class2 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State7Class3?exists><WorkCompRateClass><#list State7Class3 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State7Class4?exists><WorkCompRateClass><#list State7Class4 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State7Class5?exists><WorkCompRateClass><#list State7Class5 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
				</WorkCompRateState>
			</WorkCompLineBusiness>
			</#if>
			</#list></#if>
			<#--State 8====================================================================================================================================================================-->
			<#if State8?exists><#list State8 as result><#if notEmpty(result.atrib,result.value)>
			<WorkCompLineBusiness>
				<LOBCd>WC</LOBCd>
				<WorkCompRateState>
					<${result.atrib}>${result.value}</${result.atrib}>
					<CreditOrSurcharge>
						<CreditSurchargeCd>EMOD</CreditSurchargeCd>
						<NumericValue>
							<#list State8EMOD as result><${result.atrib}>${result.value}</${result.atrib}></#list>
							</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
							<#list State8LD as result><${result.atrib}>${result.value}</${result.atrib}></#list>
					<NumericValue>
							<#list State8LDValue as result><${result.atrib}>${result.value}</${result.atrib}></#list>
					</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
						<CreditSurchargeCd>MRAP</CreditSurchargeCd>
						<NumericValue>
							<#list State8MRAP as result><${result.atrib}>${result.value}</${result.atrib}></#list>
						</NumericValue>
					</CreditOrSurcharge>
					<#if State8Class1?exists><WorkCompRateClass><#list State8Class1 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State8Class2?exists><WorkCompRateClass><#list State8Class2 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State8Class3?exists><WorkCompRateClass><#list State8Class3 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State8Class4?exists><WorkCompRateClass><#list State8Class4 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State8Class5?exists><WorkCompRateClass><#list State8Class5 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
				</WorkCompRateState>
			</WorkCompLineBusiness>
			</#if>
			</#list></#if>
			<#--State 9====================================================================================================================================================================-->
			<#if State9?exists><#list State9 as result><#if notEmpty(result.atrib,result.value)>
			<WorkCompLineBusiness>
				<LOBCd>WC</LOBCd>
				<WorkCompRateState>
					<${result.atrib}>${result.value}</${result.atrib}>
					<CreditOrSurcharge>
						<CreditSurchargeCd>EMOD</CreditSurchargeCd>
						<NumericValue>
							<#list State9EMOD as result><${result.atrib}>${result.value}</${result.atrib}></#list>
							</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
							<#list State9LD as result><${result.atrib}>${result.value}</${result.atrib}></#list>
					<NumericValue>
							<#list State9LDValue as result><${result.atrib}>${result.value}</${result.atrib}></#list>
					</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
						<CreditSurchargeCd>MRAP</CreditSurchargeCd>
						<NumericValue>
							<#list State9MRAP as result><${result.atrib}>${result.value}</${result.atrib}></#list>
						</NumericValue>
					</CreditOrSurcharge>
					<#if State9Class1?exists><WorkCompRateClass><#list State9Class1 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State9Class2?exists><WorkCompRateClass><#list State9Class2 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State9Class3?exists><WorkCompRateClass><#list State9Class3 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State9Class4?exists><WorkCompRateClass><#list State9Class4 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State9Class5?exists><WorkCompRateClass><#list State9Class5 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
				</WorkCompRateState>
			</WorkCompLineBusiness>
			</#if>
			</#list></#if>
			<#--State 10====================================================================================================================================================================-->
			<#if State10?exists><#list State10 as result><#if notEmpty(result.atrib,result.value)>
			<WorkCompLineBusiness>
				<LOBCd>WC</LOBCd>
				<WorkCompRateState>
					<${result.atrib}>${result.value}</${result.atrib}>
					<CreditOrSurcharge>
						<CreditSurchargeCd>EMOD</CreditSurchargeCd>
						<NumericValue>
							<#list State10EMOD as result><${result.atrib}>${result.value}</${result.atrib}></#list>
							</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
							<#list State10LD as result><${result.atrib}>${result.value}</${result.atrib}></#list>
					<NumericValue>
							<#list State10LDValue as result><${result.atrib}>${result.value}</${result.atrib}></#list>
					</NumericValue>
					</CreditOrSurcharge>
					<CreditOrSurcharge>
						<CreditSurchargeCd>MRAP</CreditSurchargeCd>
						<NumericValue>
							<#list State10MRAP as result><${result.atrib}>${result.value}</${result.atrib}></#list>
						</NumericValue>
					</CreditOrSurcharge>
					<#if State10Class1?exists><WorkCompRateClass><#list State10Class1 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State10Class2?exists><WorkCompRateClass><#list State10Class2 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State10Class3?exists><WorkCompRateClass><#list State10Class3 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State10Class4?exists><WorkCompRateClass><#list State10Class4 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
					<#if State10Class5?exists><WorkCompRateClass><#list State10Class5 as result><#if notEmpty(result.atrib,result.value)>
							<${result.atrib}>${result.value}</${result.atrib}>
							</#if></#list></WorkCompRateClass></#if>
				</WorkCompRateState>
			</WorkCompLineBusiness>
			</#if>
			</#list></#if>
		</LargeDeductibleCreditCalculationRq>
	</InsuranceSvcRq>
</ACORD>