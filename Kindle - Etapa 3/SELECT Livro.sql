SELECT * 
FROM livro AS l
INNER JOIN editora ON id_pessoa = id_editora
INNER JOIN genero_livro AS gl ON gl.id_livro = l.id_livro
INNER JOIN escritor_livro AS el ON el.id_livro = l.id_livro;