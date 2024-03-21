import { useState } from "react";
import "./styles/FormScenario.css";
import { useNavigate } from "react-router-dom";

function FormLesson() {
  const navigate = useNavigate();
  const [topicValue, setTopicValue] = useState("");

  const handleTopicChange = (e: any) => {
    setTopicValue(e.target.value);
  };

  //   const handleSubmit = (e: any) => {
  //     e.preventDefault();
  //     console.log("Submitted value:", topicValue);
  //     navigate("/");
  //   };

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/api/v1/lessons", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ name: topicValue, description: topicValue }),
      });

      if (!response.ok) {
        throw new Error("Network response was not ok");
      }

      const responseData = await response.json(); // Konwertuj odpowiedź na obiekt JSON
      const lessonId = responseData.id; // Wyłuskaj ID z obiektu odpowiedzi

      // Resetuj pole input po udanym przesłaniu
      setTopicValue("");
      navigate(`/lessons/${lessonId}`);
    } catch (error) {
      console.error("Error submitting lesson:", error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="form-tile">
        <label className="bigText">
          Enter topic:
          <input
            type="text"
            value={topicValue}
            onChange={handleTopicChange}
            className="input-text"
          />
        </label>
      </div>
      <button type="submit" className="button-send">
        GENERUJ!
      </button>
    </form>
  );
}

export default FormLesson;
