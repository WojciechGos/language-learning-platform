import React, { useState } from "react";
import { NavLink } from "react-router-dom";
import "./styles/Navigation.css";

const Navigation: React.FC = () => {
  const [isOpen, setIsOpen] = useState(true);

  const toggleMenu = () => {
    setIsOpen(!isOpen);
  };

  return (
    <div className={`navigation ${isOpen ? "open" : ""}`}>
      <button onClick={toggleMenu} className="toggle-button">
        ☰
      </button>
      <ul className="menu">
        <li>
          <NavLink
            to="/"
            className={({ isActive }) => {
              return isActive ? "active-link" : "";
            }}
          >
            Dashboard
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/classes"
            className={({ isActive }) => {
              return isActive ? "active-link" : "";
            }}
          >
            Classes
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/activities"
            className={({ isActive }) => {
              return isActive ? "active-link" : "";
            }}
          >
            Activities
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/stats"
            className={({ isActive }) => {
              return isActive ? "active-link" : "";
            }}
          >
            Stats
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/scenarios"
            className={({ isActive }) => {
              return isActive ? "active-link" : "";
            }}
          >
            Scenarios
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/assistant"
            className={({ isActive }) => {
              return isActive ? "active-link" : "";
            }}
          >
            Assistant
          </NavLink>
        </li>
        <li>
          <p style={{ color: "white" }}>__________</p>
        </li>
        <li>
          <NavLink
            to="/tutor"
            className={({ isActive }) => {
              return isActive ? "active-link" : "";
            }}
          >
            Tutor
          </NavLink>
        </li>
        <li>
          <a href="/">Logout</a>
        </li>
        {/* Dodaj więcej pozycji menu, jeśli potrzebujesz */}
      </ul>
    </div>
  );
};

export default Navigation;
