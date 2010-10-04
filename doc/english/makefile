default: compile clean

compile:
	xelatex frunge.tex
	bibtex frunge.aux
	xelatex frunge.tex
	xelatex frunge.tex

clean:
	rm -f *.aux *.bbl *.blg *-blx.bib *.glo *.gls *.idx *.ilg *.ind *.log *.lof *.nav *.off *.out *.snm *.toc

