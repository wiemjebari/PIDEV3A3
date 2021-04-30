<?php


namespace App\Form;


use App\Entity\SearchData;
use App\Entity\Categorie;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\CheckboxType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;


class SearchType extends AbstractType
{

    public function ConfigureOptions(OptionsResolver $resolver){
        $resolver -> setDefaults ([
            'data_class' =>SearchData::class,
            'method' => 'GET',
            'csrf_protection' =>false

        ]);}

   public function buildForm(FormBuilderInterface $builder, array $options)
   {
     $builder
         ->add('q',TextType::class,[
         'label' =>false,
           'required' => false ,
          'attr'=>[
              'placeholder'=>'Rechercher'
   ]] )

         ->add('min', NumberType::class,[

             'label' =>false,
             'required' => false ,
             'attr'=>[
                 'placeholder'=>'Prix min']
         ])
         ->add('max', NumberType::class,[

             'label' =>false,
             'required' => false ,
             'attr'=>[
                 'placeholder'=>'Prix max']
         ])

       ->add('categories', EntityType::class,[

       'label' =>false,
       'required' => false ,
       'class'=> Categorie::class,
       'expanded' => true,
       'multiple'=>true
   ]);
   }


    public function getBlockPrefix()
    {
        return '';
    }
}