import React from "react";
import MessageBar from "./MessageBar";
import "./styles/MessageBoard.css";

const MessageBoard: React.FC = () => {
  return (
    <div className="message-board">
      <h2>Najnowsze wiadomości</h2>
      <div className="message-list">
        <MessageBar message="Za swoją aktywność otrzymałeś dodatkowe 4 punkty!" />
        <MessageBar message="Dodano nowe lekcje powtórzeniowe, sprawdź je teraz!" />
        <MessageBar message="Zostałeś przypisany do poziomu B1." />
        {/* Add more messages as needed */}
      </div>
    </div>
  );
};

export default MessageBoard;
