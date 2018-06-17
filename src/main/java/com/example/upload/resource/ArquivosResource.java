package com.example.upload.resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/arquivos")
public class ArquivosResource {
	
	@PostMapping
	public void upload(@RequestParam MultipartFile arquivo) 
	{
		this.salvarArquivo(arquivo);
	}
	
	
	private void salvarArquivo(MultipartFile arquivo)
	{
		Path diretorioRaiz = Paths.get("C:\\Users\\helde\\Desenvolvimento\\Cursos\\algaworks\\uploadComAngularSpring\\arquivo");
		try 
		{
			Files.createDirectories(diretorioRaiz);
		} 
		catch (IOException e) 
		{
			throw new RuntimeException("Problemas ao criar diretorio raiz", e);
		}
		Path arquivoPath = diretorioRaiz.resolve(arquivo.getOriginalFilename());
		try 
		{
			arquivo.transferTo(arquivoPath.toFile());
		} 
		catch (Exception e) 
		{
			throw new RuntimeException("Problemas ao Salvar o arquivo.", e);
		}
	}

}
