"""Run representative menu actions against the compiled Java example."""
from pathlib import Path
import subprocess

ROOT = Path(__file__).resolve().parents[1]
ENTRY = 'Manager'
CASES = [('9\n', ['Thank You'], []), ('1\npassword\nBeta Film\nActor B\nActress B\nDrama\n2022\n1\npassword\nAlpha Film\nActor A\nActress A\nComedy\n2021\n4\n1\n3\nAlpha Film\n2\npassword\nAlpha Film\n3\nAlpha Film\n9\n', ['New movie has been added', 'Alpha Film Actor A Actress A Comedy 2021\nBeta Film Actor B Actress B Drama 2022', 'Alpha Film has been deleted', 'MovieAlpha Film not found'], []), ('1\nwrong\n9\n', ['Entered password is wrong'], ['New movie has been added'])]
for input_text, expected, absent in CASES:
    result = subprocess.run(["java", "-cp", str(ROOT / "build"), ENTRY],
                            input=input_text, text=True, capture_output=True,
                            timeout=10, check=True)
    for text in expected:
        assert text in result.stdout, f"Missing {text!r}: {result.stdout}"
    for text in absent:
        assert text not in result.stdout, f"Unexpected {text!r}: {result.stdout}"
print(f"Passed {len(CASES)} menu checks.")
