const url = 'http://localhost:8083/revature_ers/';

let user = null;
document.getElementById("loginButton").addEventListener('click', validateLoginForm);
document.getElementById("employeeNewRequestButton").addEventListener('click', validateEmployeeNewRequestForm);
document.getElementById("managerNewRequestButton").addEventListener('click', validateManagerNewRequestForm);
document.getElementById("viewAllRequestsButton").addEventListener('click', validateViewAllRequestsForm);

document.getElementById("login_navbar").style.display = "block";
document.getElementById("employee_page").style.display = "none";
document.getElementById("manager_page").style.display = "none";
document.getElementById("tableDisplay").style.display = "none"; 

function validateViewAllRequestsForm() {
  let employeeId = document.getElementById("byEmployee").value;
  console.log(employeeId);
  let byAll = document.getElementById("viewAllRadio").value;
  if(byAll==null && (employeeId>1000 || employeeId<100)) {
    $("#employyIdErrorModal").modal("show");
    return false;
  } else {
    if (employeeId==0) {
      getRequestHistory();
    } else {
      console.log("in the else condition headed to oneEmployeeHistory");
      oneEmployeeHistory(employeeId);
    }
    
  }
};

async function oneEmployeeHistory(employeeId) {
  document.getElementById("tableEmployeeUserMessage").innerHTML="Results for: "+user.firstName+" "+user.lastName;
    try {
      resp = await fetch(url+'history',
      {
      method:'POST',
      body: JSON.stringify(employeeId),
      credentials:'include'
      // Credentials:include will ensure that the cookie is captured for future fectch requests
      // It will also require this value in order to send the cookie back.
    });  
  } 
  catch(err) {
    document.getElementById("serverErrorPara").innerHTML = err;
    $("#serverErrorModal").modal("show"); 
  }
  if(resp.status===200) {
  tableData = await resp.json();
  buildHistoryTable(tableData);
  }
}

function validateLoginForm() {
  let userName = document.getElementById("userName").value;
  let password = document.getElementById("password").value;
  if (userName == "" || password == "") {
    $("#loginErrorModal").modal("show");
    return false;
  } else loginFunc();

};

function validateEmployeeNewRequestForm() {
   let reimbAmount = document.getElementById("employeeReimbAmount").value;
   let reimbDescription = document.getElementById("employeeReimbDesc").value;
   if(reimbAmount < 0 || reimbAmount > 10000 || reimbDescription == "") {
    document.getElementById("reimbFormValidationModalPara").innerHTML = reimbAmount + reimbDescription;
    $("#reimbFormValidationErrorModal").modal("show");
    return false;
   } else newRequestFunc();
};

function validateManagerNewRequestForm() {
  let reimbAmount = document.getElementById("managerReimbAmount").value;
  let reimbDescription = document.getElementById("managerReimbDesc").value;
  if(reimbAmount < 0 || reimbAmount > 10000 || reimbDescription == "") {
   document.getElementById("reimbFormValidationModalPara").innerHTML = reimbAmount + reimbDescription;
   $("#reimbFormValidationErrorModal").modal("show");
   return false;
  } else newRequestFunc();
};

async function sendProcessRequest(array) {
  
  let editedCount;
  let resp;

  try {
    resp = await fetch(url+'process',
    {
        method:'POST',
        body: JSON.stringify(array),
        credentials:'include'
        // Credentials:include will ensure that the cookie is captured for future fectch requests
        // It will also require this value in order to send the cookie back.
    });  
   } 
    catch(err) {
      document.getElementById("serverErrorPara").innerHTML = err;
      $("#serverErrorModal").modal("show"); 
    }
  if(resp.status===200) {
    editedCount = await resp.json();

    // add the number of edited lines here for display on the modal
    document.getElementById("generalSuccessModalParagraph").innerHTML="Total number of requests updated the DB: "+editedCount;
    $("#genericSuccessModal").modal("show"); 
        // code block
    } else {
    let paragraph = document.createElement("p");
    paragraph.innerHTML = "Invalid Login.";
      $("#loginErrorModal").modal("show");
  }
};

function getRequestStatus() {
  // indicate what kind of table is needed
  let urlStatusRequest = url+"status";
  // call the getTable function
  let tableData = getTable(urlStatusRequest);
};

function getRequestHistory() {
  // indicate what kind of table is needed
  let urlHistoryRequest = url+"history";
  // call the getTable function
  let tableData = getTable(urlHistoryRequest);
};

function getRequestsToProcess() {
  // indicate what kind of table is needed
  let urlProcessRequest = url+"pending";
  // call the getTable function
  let tableData = getTable(urlProcessRequest);
};

async function getTable(urlSpecific) {
  // make a request for user table history to server
  document.getElementById("tableEmployeeUserMessage").innerHTML="Results for: "+user.firstName+" "+user.lastName;
  let resp;
  console.log(urlSpecific);
  resp = await fetch(urlSpecific, {credentials: 'include'});

  if(resp.status === 200){
    console.log(resp);
    let tableData = await resp.json();

    switch(urlSpecific) {
      case (url+"status"):
        buildStatusTable(tableData);
        break;
      case (url+"process"):
        // Displays a success modal on completing process operation
        break;
      case (url+"pending"):
        buildProcessTable(tableData);
        break;
      case (url+"history"):
        buildHistoryTable(tableData);
    }
  document.getElementById("employee_page").style.display = "none";
  document.getElementById("manager_page").style.display = "none";
  document.getElementById("tableDisplay").style.display = "block";  
  }
};

function buildStatusTable(json) {

  document.getElementById("tableDisplaySubmitButton1").style.display = "none";
  document.getElementById("tableDisplaySubmitButton2").style.display = "none";
  
  let headerRow = document.createElement("tr");
  let hcell = document.createElement("th");
  hcell.innerHTML = "Request ID"
  headerRow.appendChild(hcell);

  let hcell1 = document.createElement("th");
  hcell1.innerHTML = "Amount"
  headerRow.appendChild(hcell1);

  let hcell2 = document.createElement("th");
  hcell2.innerHTML = "Details"
  headerRow.appendChild(hcell2);

  let hcell3 = document.createElement("th");
  hcell3.innerHTML = "Type"
  headerRow.appendChild(hcell3);

  let hcell4 = document.createElement("th");
  hcell4.innerHTML = "Submitted Date"
  headerRow.appendChild(hcell4);

  let hcell5 = document.createElement("th");
  hcell5.innerHTML = "Status"
  headerRow.appendChild(hcell5);

  let hcell6 = document.createElement("th");
  hcell6.innerHTML = "Resolver ID"
  headerRow.appendChild(hcell6);

  let hcell7 = document.createElement("th");
  hcell7.innerHTML = "Resolved Date"
  headerRow.appendChild(hcell7);
  document.getElementById("displyTableHeader").appendChild(headerRow);  

  for(let request of json){
    console.log(request);
    let row = document.createElement("tr");

    let cell = document.createElement("td");
    cell.innerHTML = request.reimbId;
    row.appendChild(cell);
    
    let cell2 = document.createElement("td");
    cell2.innerHTML = "$"+request.reimbAmount;
    row.appendChild(cell2);

    let cell3 = document.createElement("td");
    cell3.innerHTML = request.reimbDescription;
    row.appendChild(cell3);

    let cell9 = document.createElement("td");
    let reimbTypeAsString = getReimbTypeAsString(request.reimbTypeId);
    cell9.innerHTML = reimbTypeAsString;
    row.appendChild(cell9);

    let cell5 = document.createElement("td");
    cell5.innerHTML = getDateObjectFromTS(request.requestDate);
    row.appendChild(cell5);
    
    let cell6 = document.createElement("td");
    cell6.innerHTML = getReimbStatusAsString(request.reimbStatusId);
    row.appendChild(cell6);

    if(request.reimbStatusId == 1) {
      let cell7 = document.createElement("td");
      cell7.innerHTML =  "";
      row.appendChild(cell7);

      let cell8 = document.createElement("td");
      cell8.innerHTML = "";
      row.appendChild(cell8);
    } else {
      let cell7 = document.createElement("td");
      cell7.innerHTML = request.reimbResolver;
      row.appendChild(cell7);

      let cell8 = document.createElement("td");
      cell8.innerHTML = getDateObjectFromTS(request.processedDate);
      row.appendChild(cell8);
    }
    document.getElementById("displayTableBody").appendChild(row);     
  };
  document.getElementById("employee_page").style.display = "none";
  document.getElementById("manager_page").style.display = "none";
  document.getElementById("tableDisplay").style.display = "block"; 
};

function buildProcessTable(json) {

  document.getElementById("tableDisplaySubmitButton1").style.display = "block";
  document.getElementById("tableDisplaySubmitButton2").style.display = "block";
  document.getElementById("employee_page").style.display = "none";
  document.getElementById("manager_page").style.display = "none";
  document.getElementById("tableDisplay").style.display = "block"; 

    let count = 0; // global access so that other functions have access to it
    let headerRow = document.createElement("tr");
    let hcell = document.createElement("th");
    hcell.innerHTML = "Request ID"
    headerRow.appendChild(hcell);

    let hcell1 = document.createElement("th");
    hcell1.innerHTML = "Amount"
    headerRow.appendChild(hcell1);

    let hcell2 = document.createElement("th");
    hcell2.innerHTML = "Details"
    headerRow.appendChild(hcell2);

    let hcell3 = document.createElement("th");
    hcell3.innerHTML = "Type"
    headerRow.appendChild(hcell3);

    let hcell4 = document.createElement("th");
    hcell4.innerHTML = "Submitted Date"
    headerRow.appendChild(hcell4);

    let hcell5 = document.createElement("th");
    hcell5.innerHTML = "Requestor ID and Name:"
    headerRow.appendChild(hcell5);

    let hcell6 = document.createElement("th");
    hcell6.innerHTML = "Approve/Disapprove"
    headerRow.appendChild(hcell6);
    document.getElementById("displyTableHeader").appendChild(headerRow);  

    let toAdd = document.createDocumentFragment();
    for(let request of json){

      let idLabel = "idLabel"+count;
      let optionLabel = "optionLabel"+count;
      count++;
      let row = document.createElement("tr");

      let cell = document.createElement("td");
      cell.innerHTML = request.reimbId;
      cell.setAttribute("id", idLabel);
      row.appendChild(toAdd.appendChild(cell));
      
      let cell2 = document.createElement("td");
      cell2.innerHTML = "$"+request.reimbAmount;
      row.appendChild(toAdd.appendChild(cell2));

      let cell3 = document.createElement("td");
      cell3.innerHTML = request.reimbDescription;
      row.appendChild(toAdd.appendChild(cell3));

      let cell9 = document.createElement("td");
      let reimbTypeAsString = getReimbTypeAsString(request.reimbTypeId);
      cell9.innerHTML = reimbTypeAsString;
      row.appendChild(toAdd.appendChild(cell9));

      let cell5 = document.createElement("td");
      cell5.innerHTML = getDateObjectFromTS(request.requestDate);
      row.appendChild(toAdd.appendChild(cell5));

      let cell7 = document.createElement("td");
      cell7.innerHTML = request.reimbAuthor+": "+request.first_name+" "+request.last_name;
      row.appendChild(toAdd.appendChild(cell7));

      let cell8 = document.createElement("td");
      let answer1 = document.createElement("option");
      answer1.innerHTML="";
      let approveOption = document.createElement("option");
      approveOption.innerHTML="Approve";
      let denyOption = document.createElement("option");
      denyOption.innerHTML = "Deny";
      let select = document.createElement("select");
      select.classList.add("form-control");
      select.setAttribute("id", optionLabel);
      select.appendChild(toAdd.appendChild(answer1)); 
      select.appendChild(toAdd.appendChild(approveOption));
      select.appendChild(toAdd.appendChild(denyOption));
      cell8.appendChild(toAdd.appendChild(select));
      row.appendChild(toAdd.appendChild(cell8));
      document.getElementById("displayTableBody").appendChild(toAdd.appendChild(row));    
    };
    document.appendChild(toAdd);
  // Create an json object with all the request IDs approved or denied

};

function buildHistoryTable(json) {

  document.getElementById("tableDisplaySubmitButton1").style.display = "none";
  document.getElementById("tableDisplaySubmitButton2").style.display = "none";
  document.getElementById("employee_page").style.display = "none";
  document.getElementById("manager_page").style.display = "none";
  document.getElementById("tableDisplay").style.display = "block"; 

  let headerRow = document.createElement("tr");
  let hcell = document.createElement("th");
  hcell.innerHTML = "Request ID"
  headerRow.appendChild(hcell);

  let hcell1 = document.createElement("th");
  hcell1.innerHTML = "Amount"
  headerRow.appendChild(hcell1);

  let hcell2 = document.createElement("th");
  hcell2.innerHTML = "Details"
  headerRow.appendChild(hcell2);

  let hcell3 = document.createElement("th");
  hcell3.innerHTML = "Type"
  headerRow.appendChild(hcell3);

  let hcell4 = document.createElement("th");
  hcell4.innerHTML = "Submitted Date"
  headerRow.appendChild(hcell4);

  let hcell5 = document.createElement("th");
  hcell5.innerHTML = "Status"
  headerRow.appendChild(hcell5);

  let hcell8 = document.createElement("th");
  hcell8.innerHTML = "Requestor ID and Name:"
  headerRow.appendChild(hcell8);

  let hcell6 = document.createElement("th");
  hcell6.innerHTML = "Resolver ID and Name"
  headerRow.appendChild(hcell6);

  let hcell7 = document.createElement("th");
  hcell7.innerHTML = "Resolved Date"
  headerRow.appendChild(hcell7);
  document.getElementById("displyTableHeader").appendChild(headerRow); 

  for(let request of json){
    let row = document.createElement("tr");

    let cell = document.createElement("td");
    cell.innerHTML = request.reimbId;
    row.appendChild(cell);
    
    let cell2 = document.createElement("td");
    cell2.innerHTML = "$"+request.reimbAmount;
    row.appendChild(cell2);

    let cell3 = document.createElement("td");
    cell3.innerHTML = request.reimbDescription;
    row.appendChild(cell3);

    let cell9 = document.createElement("td");
    let reimbTypeAsString = getReimbTypeAsString(request.reimbTypeId);
    cell9.innerHTML = reimbTypeAsString;
    row.appendChild(cell9);

    let cell5 = document.createElement("td");
    cell5.innerHTML = getDateObjectFromTS(request.requestDate);
    row.appendChild(cell5);
    
    let cell6 = document.createElement("td");
    cell6.innerHTML = getReimbStatusAsString(request.reimbStatusId);
    row.appendChild(cell6);

    let cell1 = document.createElement("td");
    cell1.innerHTML = request.reimbAuthor+": "+request.author_first_name+" "+request.author_last_name;
    row.appendChild(cell1);

    if(request.reimbStatusId == 1) {
      let cell7 = document.createElement("td");
      cell7.innerHTML =  "";
      row.appendChild(cell7);

      let cell8 = document.createElement("td");
      cell8.innerHTML = "";
      row.appendChild(cell8);
    } else {
      let cell7 = document.createElement("td");
      cell7.innerHTML = request.reimbResolver+": "+request.process_first_name+" "+request.process_last_name;
      row.appendChild(cell7);

      let cell8 = document.createElement("td");
      cell8.innerHTML = getDateObjectFromTS(request.processedDate);
      row.appendChild(cell8);
    }
    document.getElementById("displayTableBody").appendChild(row);     
  };

};

function getDateObjectFromTS(timestamp) {
  let ts_ms = timestamp;
  let date_ob = new Date(ts_ms);
  let year = date_ob.getFullYear();
  let month = ("0" + (date_ob.getMonth() + 1)).slice(-2);
  let date = ("0" + date_ob.getDate()).slice(-2);
  let hours = ("0" + date_ob.getHours()).slice(-2);
  let minutes = ("0" + date_ob.getMinutes()).slice(-2);
  let seconds = ("0" + date_ob.getSeconds()).slice(-2);
    return (month + "-" + date + "-" + year + " " + hours + ":" + minutes + ":" + seconds);
};

function getReimbTypeAsString(typeId) {
  let reimbType;
  switch (typeId) {
    case 1:
      reimbType = "Lodging";
      break;
    case 2:
      reimbType = "Travel";
      break;
    case 3:
      reimbType = "Food";
      break;
    case 4:
      reimbType = "Other";
  };
  return reimbType;
};

function getReimbStatusAsString(statusId) {
  let reimbStatus;
  switch (statusId) {
    case 1:
      reimbStatus = "Pending";
      break;
    case 2:
      reimbStatus = "Approved";
      break;
    case 3:
      reimbStatus = "Denied";
      break;
  };
  return reimbStatus;
};

// function getFile(isEmployee){
//   let file;
//   if (isEmployee="receipt") {
//     file = document.getElementById("receipt").value;
//   } else file = document.getElementById("receipt2").value;

//   console.log(file); // see the FileList
//   let BtnEle = document.querySelector(".Btn");
//   let resEle = gdocument.querySelector(".result");
//   BtnEle.addEventListener("click", () => {
//      fetch("https://i.picsum.photos/id/222/300/300.jpg")
//      .then(function (response) {
//         return response.blob();
//      })
//      .then(function (blob) {
//         resEle.innerHTML = "blob.size = " + blob.size + "<br>";
//         resEle.innerHTML += "blob.type = " + blob.type + "<br>";
//      });
//   });
  // manually create a new file obj for each File in the FileList

//  let   fileObject = {
//         'lastMod'    : file.lastModified,
//         'lastModDate': file.lastModifiedDate,
//         'name'       : file.name,
//         'size'       : file.size,
//         'type'       : file.type,
//   }

//   //stringify array
//   console.log(JSON.stringify(fileObject));
//   return file;
// }

async function newRequestFunc() {
  let resp;
 // let receipt = getFile();
  let newReimbRequest; 
  let header = document.createElement("tr");
  let toAdd = document.createDocumentFragment();
  let body = document.createElement("tr");
  let tableHeader = document.getElementById("displayTableModalHeader");
  let tableBody = document.getElementById("displayTableModalBody");

  if(user.role === 2) {
      newReimbRequest = {
        reimbAmount: document.getElementById('managerReimbAmount').value,
        reimbDescription: document.getElementById("managerReimbDesc").value,
        reimbAuthor: user.iD,    
        reimbTypeId: (document.getElementById("managerReimbursementType").selectedIndex+1)
     };
     console.log(file);
     closeForm();
  } else {
    let file = document.getElementById("receipt2");
    newReimbRequest = {
      reimbAmount: document.getElementById('employeeReimbAmount').value,
      reimbDescription: document.getElementById("employeeReimbDesc").value,
      reimbAuthor: user.iD,
      reimbTypeId: (document.getElementById("employeeReimbursementType").selectedIndex+1)
    };
    closeEmployeeForm();
  }
  resp = await fetch(url+'request', {
    method:'POST',
    body: JSON.stringify(newReimbRequest),
    credentials:'include'
  });

  if(resp.status === 200){
    // Clear the table if needed
      try {
        while(tableHeader.hasChildNodes()) {
          tableHeader.removeChild(tableHeader.firstChild);
        };
        while(tableBody.hasChildNodes()) {
          tableBody.removeChild(tableBody.firstChild);
        }
      } catch(err) {};

    // Get data
    let newReimbResponse = await resp.json();

    // display the new request and report as successfully entered 
    
    let cell10 = document.createElement("th");
    cell10.innerHTML = "Request ID";
    header.appendChild(cell10);
    
    let cell11 = document.createElement("th");
    cell11.innerHTML = "Requestor Name";
    header.appendChild(cell11);

    let cell12 = document.createElement("th");
    cell12.innerHTML = "Amount";
    header.appendChild(cell12);

    let cell13 = document.createElement("th");
    cell13.innerHTML = "Description";
    header.appendChild(cell13);

    let cell14 = document.createElement("th");
    cell14.innerHTML = "Request Type";
    header.appendChild(cell14);

    let cell15 = document.createElement("th");
    cell15.innerHTML = "Requested Date";
    header.appendChild(cell15);

    let cell16 = document.createElement("th");
    cell16.innerHTML = "Status";
    header.appendChild(cell16);
    document.getElementById("displayTableModalHeader").appendChild(header);

  
    let cell1 = document.createElement("td");
    cell1.innerHTML = newReimbResponse.reimbId;
    body.appendChild(toAdd.appendChild(cell1));
    
    let cell = document.createElement("td");
    cell.innerHTML = (user.firstName+" "+user.lastName);
    body.appendChild(toAdd.appendChild(cell));

    let cell2 = document.createElement("td");
    cell2.innerHTML = (newReimbResponse.reimbAmount);
    body.appendChild(toAdd.appendChild(cell2));

    let cell3 = document.createElement("td");
    cell3.innerHTML = newReimbResponse.reimbDescription;
    body.appendChild(toAdd.appendChild(cell3));

    let cell4 = document.createElement("td");
    let reimbType;
    switch (newReimbResponse.reimbTypeId) {
      case 1:
        reimbType = "Lodging";
        break;
      case 2:
        reimbType = "Travel";
        break;
      case 3:
        reimbType = "Food";
        break;
      case 4:
        reimbType = "Other";
    }
    cell4.innerHTML = reimbType;
    body.appendChild(toAdd.appendChild(cell4));

    let cell5 = document.createElement("td");
    // convert unix timestamp to milliseconds
    let ts_ms = newReimbResponse.requestDate*1000;
    // initialize new Date object
    let date_ob = new Date(ts_ms);
    cell5.innerHTML = date_ob.toUTCString();
    body.appendChild(toAdd.appendChild(cell5));

    let cell6 = document.createElement("td");
    cell6.innerHTML = "Pending";
    body.appendChild(cell6);
    document.getElementById("displayTableModalBody").appendChild(toAdd.appendChild(body));
    document.getElementById("displayTableModalBody").appendChild(toAdd);

    $("#successTableModal").modal("show");
  } else {
    $("#genericErrorModal").modal("show");
    document.getElementById("generalErrorModalParagraph").innerHTML = "Request could not be processed. Please try again.";
  }
};

async function loginFunc() {

  let resp;
  let login = {
      userName: document.getElementById('userName').value,
      password: document.getElementById('password').value
  };

  try {
    resp = await fetch(url+'login',
    {
        method:'POST',
        body: JSON.stringify(login),
        credentials:'include'
        // Credentials:include will ensure that the cookie is captured for future fectch requests
        // It will also require this value in order to send the cookie back.
    });  
   } 
    catch(err) {
      document.getElementById("serverErrorPara").innerHTML = err;
      $("#serverErrorModal").modal("show"); 
    }
  if(resp.status===200) {
    user = await resp.json();

    switch(user.role) {
      case 1:
        document.getElementById("login_navbar").style.display = "none";
        document.getElementById("employee_page").style.display = "block";
        document.getElementById("manager_page").style.display = "none";
        document.getElementById("employeeUserFirstName").innerHTML="Welcome, "+user.firstName;
        break;
      case 2:
        document.getElementById("login_navbar").style.display = "none";
        document.getElementById("employee_page").style.display = "none";
        document.getElementById("manager_page").style.display = "block";
        document.getElementById("managerUserFirstName").innerHTML="Welcome, "+user.firstName;      
        break;
      default:
        // code block
    }

    //window.location.hash = "#managerMenu.html"; 
 //   document.getElementById("managerUserFirstName").innerHTML= "Welcome, "+firstName;

  } else {
    let paragraph = document.createElement("p");
    paragraph.innerHTML = "Invalid Login.";
      $("#loginErrorModal").modal("show");
  }

};

async function logoutFunc() {
  let resp = await fetch(url+'logout', {
    method:'POST',
    credentials:'include'
});
};

function openForm() {
  document.getElementById("myForm").style.display = "block";
};
  
function closeForm() {
  document.getElementById("myForm").style.display = "none";
};

function openEmployeeForm() {
  document.getElementById("myEmployeeForm").style.display = "block";
};
  
function closeEmployeeForm() {
  document.getElementById("myEmployeeForm").style.display = "none";
};

function openViewForm() {
  document.getElementById("myViewForm").style.display = "block";
};
  
function closeViewForm() {
  document.getElementById("myViewForm").style.display = "none";
};

function openViewEmployeeForm() {
  document.getElementById("myViewEmployeeForm").style.display = "block";
};
  
function closeViewEmployeeForm() {
  document.getElementById("myViewEmployeeForm").style.display = "none"; 
};

function openTable() {
  document.getElementById("employee_page").style.display = "none";
  document.getElementById("manager_page").style.display = "none"; 
  document.getElementById("tableDisplay").style.display = "block";
};
  
function checkTable() {
  // Get data from the array
  let body = document.getElementById("displayTableBody");
  let approvalArray = [];
  let requestId, isApproved, element;
  let count = body.childElementCount;

  if(count>0){
    for(let i = 0; i<count; i++) {
      element = {requestId, isApproved}; 
      element.requestId = document.getElementById("idLabel"+i).innerHTML;
      element.isApproved = document.getElementById("optionLabel"+i).selectedIndex+1;
      if(element.isApproved>1) {
        if (element.isApproved===2){
          element.isApproved=true;
        } else {
          element.isApproved=false;
        }
        approvalArray.push(element);
      }
    }
    console.log(approvalArray.length);

    if(approvalArray.length!=0){
      console.log("Sending query.")
      sendProcessRequest(approvalArray);
      closeTable();
    } else {
      // Call modal
      console.log("showing modal")
      document.getElementById("generalErrorModalParagraph").innerHTML = "No approval status changes detected. Please try again."
      $("#genericErrorModal").modal("show");
    }
  } 
};

function closeTable() {
  // Turn off table display
  let header = document.getElementById("displyTableHeader");
  let body = document.getElementById("displayTableBody");
  document.getElementById("tableDisplay").style.display = "none";
  document.getElementById("login_navbar").style.display = "none";
  // Remove previous table load
  while(header.hasChildNodes()) {
    header.removeChild(header.firstChild);
  };
  while(body.hasChildNodes()) {
    body.removeChild(body.firstChild);
  }
  // Show appropriate user options
  if(user.role === 1){
    document.getElementById("employee_page").style.display = "block";
  } else {
    document.getElementById("manager_page").style.display = "block"; 
  }
};


