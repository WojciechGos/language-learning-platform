import React from "react";
import MessageBoard from "../components/MessageBoard";
import Navigation from "../components/Navigation";

const Dashboard: React.FC = () => {
  return (
    <div>
      <Navigation />
      <h1>Podsumowanie</h1>
      <MessageBoard />
    </div>
  );
};

export default Dashboard;
