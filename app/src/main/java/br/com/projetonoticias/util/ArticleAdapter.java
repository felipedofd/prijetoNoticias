public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private List<Article> listaArtigos;

    public ArticleAdapter(List<Article> listaArtigos) {
        this.listaArtigos = listaArtigos;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View minhaView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        ArticleViewHolder viewHolder = new ArticleViewHolder(minhaView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ArticleViewHolder holder, int position) {
        String titulo = listaArtigos.get(position).title;
        holder.getTitulo().setText(titulo);
        String urlToImage = listaArtigos.get(position).urlToImage;
        Picasso.get().load(urlToImage).into(holder.getImageNews());
        Source source = listaArtigos.get(position).source;
        holder.getImageNews().setOnClickListener(new View.OnClickListener() {

