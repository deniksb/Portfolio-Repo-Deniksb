    // Import the functions you need from the SDKs you need
    import { initializeApp } from "https://www.gstatic.com/firebasejs/9.8.3/firebase-app.js";
    import { getAnalytics } from "https://www.gstatic.com/firebasejs/9.8.3/firebase-analytics.js";
    import { getStorage,ref,uploadBytes,getDownloadURL } from "https://www.gstatic.com/firebasejs/9.8.3/firebase-storage.js";
    // TODO: Add SDKs for Firebase products that you want to use
    // https://firebase.google.com/docs/web/setup#available-libraries
  
    // Your web app's Firebase configuration
    // For Firebase JS SDK v7.20.0 and later, measurementId is optional
    const firebaseConfig = {
      apiKey: "AIzaSyD3Kml9mTopyBpGUOvzHtoL6YRZIJfP_DY",
      authDomain: "servproject-352912.firebaseapp.com",
      projectId: "servproject-352912",
      storageBucket: "servproject-352912.appspot.com",
      messagingSenderId: "390235809708",
      appId: "1:390235809708:web:b6aacd13e3dc526d7bfc09",
      measurementId: "G-5E5GX013G6"
    };
  
    // Initialize Firebase
    export const firebase = initializeApp(firebaseConfig);
    export const analytics = getAnalytics(firebase);
    export const storage = getStorage(firebase, 'gs://servproject-352912.appspot.com');
    
    export function uploadFile(file,name){
        const storageRef = ref(storage, name);
        uploadBytes(storageRef, file).then((snapshot) => {
            getDownloadURL(storageRef).then((value) => {
                console.log(value);
                document.getElementById("imgUrl").value = value;
                return value;
            });
          });
    };
 