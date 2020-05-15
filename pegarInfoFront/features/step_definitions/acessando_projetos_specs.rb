Entao("que o programa ja foi executado") do
    json_file = File.new("projetos.json", "w")
end
  
Quando("acesso a url {string}") do |url|
    visit(url)
end

E("verifico se estou na pagina correta {string}") do |url|
    expect(page).to have_current_path('http://www.cmmc.com.br/'+ url.to_s)
end

Entao("Atribuo os dados a uma variavel e {string}") do |tipo_projeto|
    @projetos = Projetos.new
    @lista = []
    i = 37
    pagina = find_all("td")

    while(pagina[i].nil? !=true) do
        @lista.push(pagina[i].text)
        i = i + 1
    end
    
    if tipo_projeto == "leiOrdinaria"
        @jsonFormat = '{ "'+tipo_projeto+'":['
    else
        if tipo_projeto !="leiOrdinaria2"
            @jsonFormat = '"'+tipo_projeto+'":['
        else
            @jsonFormat = " "
        end
    end
    i = 0

    while(@lista[i].nil? !=true) do
        docNum = @lista[i].split('/')
        docFormatUrl = "http://www.cmmc.com.br/siteadmin/projetos/anexos/Pl_0"+docNum[0] + "_" + docNum[1]+".pdf"


        @jsonFormat = @jsonFormat + "{"+'"numero"'+":"+'"'+@lista[i].to_s+'"' + "," 
        @jsonFormat = @jsonFormat + '"autor"'+":"+'"'+@lista[i+1].to_s+'"' + "," 
        @jsonFormat = @jsonFormat + '"assunto"'+":"+'"'+@lista[i+2].to_s+'"' + "," 
        @jsonFormat = @jsonFormat + '"link"'+":"+'"'+docFormatUrl.to_s + '"' + "," 
        @jsonFormat = @jsonFormat + '"anotacao"'+":"+'"'+@lista[i+3].to_s+'"' + "}" 
        i = i + 4
        if @lista[i].nil? !=true 
            @jsonFormat = @jsonFormat + ','
        end 

    end
    if tipo_projeto == "leiOrdinaria"
        @jsonFormat = @jsonFormat + ","
    elsif  tipo_projeto == "resolucao"
        @jsonFormat = @jsonFormat + "]}"
    elsif tipo_projeto != "leiOrdinaria"
        @jsonFormat = @jsonFormat + "],"
    end
    
    
end

Então("Salvo em um arquivo json") do
    @projetos.salvar_em_json(@jsonFormat)
end