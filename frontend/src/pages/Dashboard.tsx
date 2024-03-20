import React from "react";
import MessageBoard from "../components/MessageBoard";
import Navigation from "../components/Navigation";

const Dashboard: React.FC = () => {
  return (
    <div>
      <Navigation />
      <h1>Podsumowanie</h1>
      <MessageBoard
        name="Najnowsze wiadomości"
        messages={[
          "Za swoją aktywność otrzymałeś dodatkowe 4 punkty!",
          "Dodano nowe lekcje powtórzeniowe, sprawdź je teraz!",
          "Zostałeś przypisany do poziomu B1.",
        ]}
      />
    </div>
  );
};

export default Dashboard;
