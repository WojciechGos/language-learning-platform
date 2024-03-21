import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import "./styles/FormScenario.css";

const Task: React.FC = () => {
  const { topicValue, id } = useParams<{ topicValue: string; id: string }>();
  const [hobbyValue, setHobbyValue] = useState("");
  const [typeValue, setTypeValue] = useState("");

  const handleHobbyChange = (e: any) => {
    setHobbyValue(e.target.value);
  };

  const handleTypeChange = (e: any) => {
    setTypeValue(e.target.value);
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/api/v1/tasks/generate`,
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({
              topic: topicValue,
              type: "past simple past continuous",
              lessonId: id,
            }),
          }
        );

        if (!response.ok) {
          throw new Error("Network response was not ok");
        }

        const responseData = await response.json(); // Konwertuj odpowiedź na obiekt JSON
        console.log(responseData);

        // Tutaj możesz obsłużyć odpowiedź jeśli jest potrzebne
      } catch (error) {
        console.error("Error:", error);
        // Tutaj możesz obsłużyć błędy sieciowe lub inne błędy
      }
    };

    fetchData();
  }, [topicValue, id]); // Zmiany topicValue lub id spowodują ponowne wywołanie useEffect

  const handleSubmit = (e: any) => {
    e.preventDefault();
    console.log("Submitted value:", hobbyValue + typeValue);
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="task-tile">
        <label className="bigText">
          Lorem Ipsum jest tekstem stosowanym jako przykładowy wypełniacz w
          przemyśle poligraficznym. Został po raz pierwszy użyty w XV w. przez
          nieznanego drukarza do wypełnienia tekstem próbnej książki. Pięć
          wieków później zaczął być _______ przemyśle elektronicznym, pozostając
          praktycznie niezmienionym. Spopularyzował się w latach 60. XX w. wraz
          z publikacją arkuszy Letrasetu, zawierających fragmenty Lorem Ipsum, a
          ostatnio z ______ różne wersje Lorem Ipsum oprogramowaniem
          przeznaczonym do realizacji druków na komputerach osobistych, jak
          Aldus PageMaker
          <input
            type="text"
            value={hobbyValue}
            onChange={handleHobbyChange}
            className="input-text"
            placeholder="answer 1"
          />
        </label>
        <label className="bigText">
          <input
            type="text"
            value={typeValue}
            onChange={handleTypeChange}
            className="input-text"
            placeholder="answer 2"
          />
        </label>
      </div>
    </form>
  );
};

export default Task;
