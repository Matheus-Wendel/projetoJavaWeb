class Projetos

    def salvar_em_json(projeto)
        puts projeto
        json_file = File.new("projetos.json", "a")
        json_file.write(projeto)
        json_file.close
    end
end