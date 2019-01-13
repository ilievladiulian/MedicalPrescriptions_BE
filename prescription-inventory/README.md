## Prescription inventory microservice
This microservice manages pharmacies, medicines and prescriptions.
Prescription inventory runs on port 8302.

### Rest API Docs

<b>Authorization</b> based on header field:
Authorization -> "Bearer username:token"

1) GET /inventory/pharmacy - returns all pharmacies
- response list of PharmacyDTO

PharmacyDTO

{

  "name": "",
  
  "address": "",
  
  "openAt": integer,
  
  "closeAt": integer,
  
  "medicineList": list of medicineDTO

}

MedicineDTO

{

  "name": "",
  
  "activeSubstance": "",
  
  "activeQuantity": integer,
  
  "productionDate": date in format yyyy-mm-dd,
  
  "expiryDate": date in format yyyy-mm-dd,
  
  "details": ""

}

2) GET /inventory/medicine - returns all medicines
- response list of medicineDTO (see above)

3) POST /inventory/pharmacy - returns 5 top pharmacies that have the most of the medicines in list
- body list of medicineDTO (see above)

- response list of 5 pharmacyDTOs (see above)

4) GET /prescription - returns all prescriptions of the current user
- response list of PrescriptionDTO

PrescriptionDTO

{

  "id": integer,
  
  "creationDate": date in format yyyy-mm-ddThh:mm:dd,
  
  "description": "",
  
  "medicineList": list of MedicineWithQuantity

}

MedicineWithQuantity

{

  "medicine": {
    "name": ""
  },
  
  "quantity": integer

}

5) GET /prescription/{id} - returns details for prescription with id {id}
- response PrescriptionDTO object (see above)

6) POST /prescription - creates prescription
- body PrescriptionDTO object (see above)

- response OperationResponse object

OperationResponse

{

  "success": boolean,
  
  "operationName": ""

}

7) PUT /prescription - updates prescription
- body PrescriptionDTO object (see above)

- response OperationResponse (see above)

8) DELETE /prescription/{id} - deletes prescription with id {id}
- response OperationResponse (see above)
