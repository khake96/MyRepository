const url = 'http://localhost:8083/RevatureERS/';

document.getElementById("loginButton").addEventListener('click', loginFunc, false);


async function loginFunc() {
    
  let usern = document.getElementById('username');
  let userp = document.getElementById('password');

  let user = {
      username: usern,
      password: userp
  };

  let resp = await fetch(url+'login', {
      method:'POST',
      body: JSON.stringify(user),
      credentials:'include'
      // Credentials:include will ensure that the cookie is captured for future fectch requests
      // It will also require this value in order to send the cookie back.
  });
  if(resp.status===200) {
//         if(data.User.role === 2) { // is manager
//           window.location.href = managerMenu.html;
//         } else if(data.User.role=== 1) { // is employee
//           window.location.href = employeeMenu.html;
//         }
  } else {
//      $("#loginErrorModal").modal("show");
  }

}


// async function logicFunc() {

//   let response = await fetch(url+'login'); // uses the fetch api and adding the endpoint name
//   if(response.status===200) { // triple equals is more efficient but double equals would be OK
//       console.log(response); // just for us to see on console
//       let data = await response.json(); // puts the json object in the respone into an array called data
//       if(data.User!=null) {
//         if(data.User.role === 2) { // is manager
//           window.location.href = managerMenu.html;
//         } else if(data.User.role=== 1) { // is employee
//           window.location.href = employeeMenu.html;
//         } else { // Not a valid user
//           $("#loginErrorModal").modal("show");
//         }
//       }
//     }
//   }
      // for(let avenger of data) {
      //     console.log(avenger);
      //     let row = document.createElement('tr');

      //     let cell = document.createElement('td');
      //     cell.innerHTML = avenger.aveId;
      //     row.appendChild(cell);
      //     let cell2 = document.createElement('td');
      //     cell2.innerHTML = avenger.aveName;
      //     row.appendChild(cell2);
      //     let cell5 = document.createElement('td');




function openForm() {
  document.getElementById("myForm").style.display = "block";
}
  
function closeForm() {
  document.getElementById("myForm").style.display = "none";
}

function openViewForm() {
  document.getElementById("myViewForm").style.display = "block";
}
  
function closeViewForm() {
  document.getElementById("myViewForm").style.display = "none";
}

function openViewEmployeeForm() {
  document.getElementById("myViewEmployeeForm").style.display = "block";
   //document.getElementById("viewAllRequestBtns").style.display = "none";
}
  
function closeViewEmployeeForm() {
  document.getElementById("myViewEmployeeForm").style.display = "none"
  //document.getElementById("viewAllRequestBtns").style.display = "block";
}


// function userLogin() {
//   // Listen for login button to be clicked
//   addEventListener()
  // catch the response object from the server
  
  // // check for valid user returned by DB
  // if(user is valid) {
  //   if(is Manager){
  //     choose the manager page to manipulate
  //     document.getElementById("managerUserFirstName")
  //     populate <span id="managerUserFirstName"></span>
  //     display manager page;
  //     } else {
  //       choose employee page to manipulate
  //       populate employee page with span with class="employeeUserFirstName"
  //       display employee page
  //     }
  //   } else {
  //     $("#loginErrorModal").modal()
  //     write an error message back to the login page and say "try again"
  //   }
