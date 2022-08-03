import { initializeApp } from "firebase/app";
import { getFirestore } from "@firebase/firestore";

const firebaseConfig = {
  apiKey: "AIzaSyCqFIZPnTqYCoE9_-zgXLYG3TaOWUCZ4vc",
  authDomain: "react-project-planner-1aec0.firebaseapp.com",
  projectId: "react-project-planner-1aec0",
  storageBucket: "react-project-planner-1aec0.appspot.com",
  messagingSenderId: "974929598040",
  appId: "1:974929598040:web:d81fdb8172f3d899d01c2d",
  measurementId: "G-784Z8GEL9C"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);

export const db = getFirestore(app);



